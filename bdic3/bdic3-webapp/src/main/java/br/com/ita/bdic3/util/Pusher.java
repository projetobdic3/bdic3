package br.com.ita.bdic3.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.mortbay.log.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Static class to send messages to Pusher's REST API.
 * 
 * Please set pusherApplicationId, pusherApplicationKey, pusherApplicationSecret accordingly
 * before sending any request. 
 * 
 * @author Stephan Scheuermann
 * Copyright 2010. Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
 */
public class Pusher {

	/**
	 *  Pusher Host name
	 */
	private final static String pusherHost = "api.pusherapp.com";
	
	/**
	 * Pusher Application Identifier
	 */
	private final static String pusherApplicationId = "77595";
	
	/**
	 * Pusher Application Key
	 */
	private final static String pusherApplicationKey = "bb07ac5c1f93b081f6f7";
	
	/**
	 * Pusher Secret
	 */
	private final static String pusherApplicationSecret = "9efd4051aca7ef07b1ef";
	
	/**
	 * Converts a byte array to a string representation
	 * @param data
	 * @return
	 */
	private static String byteArrayToString(byte[] data){
    	BigInteger bigInteger = new BigInteger(1,data);
    	String hash = bigInteger.toString(16);
    	// Zero pad it
    	while(hash.length() < 32 ){
    	  hash = "0" + hash;
    	}
    	return hash;
	}
	
	/**
	 * Returns a md5 representation of the given string
	 * @param data
	 * @return
	 */
	private static String md5Representation(String data) {
        try {
        	//Get MD5 MessageDigest
        	MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        	byte[] digest = messageDigest.digest(data.getBytes("US-ASCII"));
        	return byteArrayToString(digest);
        } catch (NoSuchAlgorithmException nsae) {
            //We should never come here, because GAE has a MD5 algorithm
        	throw new RuntimeException("No MD5 algorithm");
        } catch (UnsupportedEncodingException e) {
            //We should never come here, because UTF-8 should be available
        	throw new RuntimeException("No UTF-8");
		}	
	}
	
	/**
	 * Returns a HMAC/SHA256 representation of the given string
	 * @param data
	 * @return
	 */
    private static String hmacsha256Representation(String data) {
        try {
            // Create the HMAC/SHA256 key from application secret
            final SecretKeySpec signingKey = new SecretKeySpec( pusherApplicationSecret.getBytes(), "HmacSHA256");

            // Create the message authentication code (MAC)
            final Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            
            //Process and return data
            byte[] digest = mac.doFinal(data.getBytes("UTF-8"));
            digest = mac.doFinal(data.getBytes());
            //Convert to string
            BigInteger bigInteger = new BigInteger(1,digest);
    		return String.format("%0" + (digest.length << 1) + "x", bigInteger);            
        } catch (NoSuchAlgorithmException nsae) {
            //We should never come here, because GAE has HMac SHA256
        	throw new RuntimeException("No HMac SHA256 algorithm");
        } catch (UnsupportedEncodingException e) {
            //We should never come here, because UTF-8 should be available
        	throw new RuntimeException("No UTF-8");
		} catch (InvalidKeyException e) {
			throw new RuntimeException("Invalid key exception while converting to HMac SHA256");
		} 
    }	

    /**
     * Build query string that will be appended to the URI and HMAC/SHA256 encoded
     * @param eventName
     * @param jsonData
     * @return
     */
    private static String buildQuery(String eventName, String jsonData, String socketID){
    	StringBuffer buffer = new StringBuffer();
    	//Auth_Key
    	buffer.append("auth_key=");
    	buffer.append(pusherApplicationKey);
    	//Timestamp
    	buffer.append("&auth_timestamp=");
    	buffer.append(System.currentTimeMillis() / 1000);
    	//Auth_version
    	buffer.append("&auth_version=1.0");
    	//MD5 body
    	buffer.append("&body_md5=");
    	buffer.append(md5Representation(jsonData));
    	//Event Name
    	buffer.append("&name=");
    	buffer.append(eventName);
		//Append socket id if set
		if (!socketID.isEmpty()) {
			buffer.append("&socket_id=");
			buffer.append(socketID);
		}
		//Return content of buffer
		return buffer.toString();
    }
    
    /**
     * Build path of the URI that is also required for Authentication
     * @return
     */
    private static String buildURIPath(String channelName){
    	StringBuffer buffer = new StringBuffer();
    	//Application ID
    	buffer.append("/apps/");
    	buffer.append(pusherApplicationId);
    	//Channel name
    	buffer.append("/channels/");
    	buffer.append(channelName);
    	//Event
    	buffer.append("/events");    	
		//Return content of buffer
		return buffer.toString();    	
    }
    
    /**
     * Build authentication signature to assure that our event is recognized by Pusher
     * @param uriPath
     * @param query
     * @return
     */
    private static String buildAuthenticationSignature(String uriPath, String query){
    	StringBuffer buffer = new StringBuffer();
    	//request method
    	buffer.append("POST\n");
    	//URI Path
    	buffer.append(uriPath);
    	buffer.append("\n");
    	//Query string
    	buffer.append(query);
    	//Encode data
    	String h = buffer.toString();
    	return hmacsha256Representation(h);    	
    }
    
    /**
     * Build URI where request is send to
     * @param uriPath
     * @param query
     * @param signature
     * @return
     */
    private static URL buildURI(String uriPath, String query, String signature){
    	StringBuffer buffer = new StringBuffer();
    	//Protocol
    	buffer.append("http://");
    	//Host
    	buffer.append(pusherHost);
    	//URI Path
    	buffer.append(uriPath);
    	//Query string
    	buffer.append("?");
    	buffer.append(query);
    	//Authentication signature
    	buffer.append("&auth_signature=");
    	buffer.append(signature);
    	//Build URI
    	try {
			return new URL(buffer.toString());
		} catch (MalformedURLException e) {
			throw new RuntimeException("Malformed URI");
		}
    }
    
    /**
     * Delivers a message to the Pusher API without providing a socket_id
     * @param channel
     * @param event
     * @param jsonData
     * @return
     */
    public static HttpResponse triggerPush(String channel, String event, String jsonData){
    	try {
			return triggerPush(channel, event, jsonData, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * Delivers a message to the Pusher API
     * @param channel
     * @param event
     * @param jsonData
     * @param socketId
     * @return
     * @throws Exception 
     */
    public static HttpResponse triggerPush(String channel, String event, String jsonData, String socketId) throws Exception{
    	//Build URI path
    	String uriPath = buildURIPath(channel);
    	//Build query
    	String query = buildQuery(event, jsonData, socketId);
    	//Generate signature
    	String signature = buildAuthenticationSignature(uriPath, query);
    	//Build URI
    	URL url = buildURI(uriPath, query, signature);

		//Start request
		try {
			return sendPost(url, jsonData);
		} catch (IOException e) {
			//Log warning
			Log.warn("Pusher request could not be send to the following URI " + url.toString());
			return null;
		}    	
    }
    
    
    private static HttpResponse sendPost(URL url, String data) throws Exception {
		 
		/*String url = "http://api.pusherapp.com/apps/77595/events?"+
				"body_md5=8a3501faef6636ca9a5ebbe6f31b5409&"+
				"auth_version=1.0&"+
				"auth_key=bb07ac5c1f93b081f6f7&"+
				"auth_timestamp=1402419878&"+
				"auth_signature=815455d884c1cefca9356925c805d9700403ed183f1faf1b0fac29925f7b9665&";
	 */
 
		HttpClient client = new DefaultHttpClient();
		
		String urlStr = url.toExternalForm();
		HttpPost post = new HttpPost(urlStr);
		
		// add header
		post.addHeader("Content-Type", "application/json");
 
		//List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		//urlParameters.add(new BasicNameValuePair("data", "{\\\"message\\\":\\\"hello world\\\"}"));
 
		//post.setEntity(new UrlEncodedFormEntity(urlParameters));	
				
		post.setEntity(new StringEntity(data));
 
		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
                                    response.getStatusLine().getStatusCode());
 
		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		System.out.println(result.toString());
		return response;
 
	}

}
