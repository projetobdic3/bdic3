package br.com.ita.bdic3.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

@Controller
@RequestMapping()
public class MapaViewController {

	private static final String VIEW_MAPA_HIST = "redirect:/mapa-hist/index.html";
	private static final String VIEW_MAPA_REALTIME = "redirect:/mapa-realtime/index.html";


	@RequestMapping(value = "/mapaHistorico", method = RequestMethod.GET)
	public String mapaHistorico(Model model) {
		return VIEW_MAPA_HIST;		
	}
	
	@RequestMapping(value = "/mapaRealtime", method = RequestMethod.GET)
	public String mapaRealtime(Model model) {
		return VIEW_MAPA_REALTIME;		
	}
	
	/*@RequestMapping(value = "/lancaFraude", method = RequestMethod.GET)
	public String lancaFraude() throws Exception {
		sendPost();
		
		return VIEW_MAPA_REALTIME;		
	}*/

	/*@RequestMapping(method = RequestMethod.POST)
	public String validarCliente(@ModelAttribute("contestacaoVO") ContestacaoVO contestacaoVO, Model model) {
		if (contestacaoService.validarCliente(contestacaoVO)) {
			return VIEW_CONTESTACAO_FRAUDE;
		} else {
			model.addAttribute("mensagemErro", "Dados Invalidos");
			return VIEW_CONTESTACAO;
		}
	}*/

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// public String products(@PathVariable("id") String id, Model model) {
	// Produto produto = produtoService.findById(Long.parseLong(id));
	// model.addAttribute("pedidoVO", new PedidoVO());
	// model.addAttribute("produto", produto);
	// return VIEW_COMPRA;
	

	/*private final String USER_AGENT = "Mozilla/5.0";
	
	// HTTP GET request
		private void sendGet() throws Exception {
	 
			String url = "http://api.pusherapp.com/apps/77595/events?"+
				"body_md5=8a3501faef6636ca9a5ebbe6f31b5409&"+
				"auth_version=1.0&"+
				"auth_key=bb07ac5c1f93b081f6f7&"+
				"auth_timestamp=1402419878&"+
				"auth_signature=815455d884c1cefca9356925c805d9700403ed183f1faf1b0fac29925f7b9665&";
	 
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
	 
			// add request header

			request.addHeader("Content-Type", "application/json");

			HttpResponse response = client.execute(request);
	 
			System.out.println("\nSending 'GET' request to URL : " + url);
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
	 
		}
		
		
		private void sendPost() throws Exception {
			 
			String url = "http://api.pusherapp.com/apps/77595/events?"+
					"body_md5=8a3501faef6636ca9a5ebbe6f31b5409&"+
					"auth_version=1.0&"+
					"auth_key=bb07ac5c1f93b081f6f7&"+
					"auth_timestamp=1402419878&"+
					"auth_signature=815455d884c1cefca9356925c805d9700403ed183f1faf1b0fac29925f7b9665&";
		 
	 
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
	 
			// add header
			post.addHeader("Content-Type", "application/json");
	 
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("data", "{\\\"message\\\":\\\"hello world\\\"}"));
	 
			post.setEntity(new UrlEncodedFormEntity(urlParameters));

	 
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
	 
		}*/
}