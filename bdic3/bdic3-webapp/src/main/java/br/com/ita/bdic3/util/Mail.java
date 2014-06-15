package br.com.ita.bdic3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class Mail {
	
	public static final String PROJETOBDIC3_EMAIL = "projetobdic3@gmail.com";
	
	@Autowired
	private MailSender mailSender;
	 
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String to, String subject, String msg) {
 
		SimpleMailMessage message = new SimpleMailMessage();
 
		message.setFrom(PROJETOBDIC3_EMAIL);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		
		try {
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public static void main( String[] args ) {
    	Mail mm = new Mail();
        mm.sendMail(PROJETOBDIC3_EMAIL,
    		   "Teste Email", 
    		   "Teste Envio de Email Projeto BDIC3!");
    }

}
