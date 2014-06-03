package br.com.ita.bdic3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class Mail {
	
	@Autowired
	private MailSender mailSender;
	 
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String from, String to, String subject, String msg) {
 
		SimpleMailMessage message = new SimpleMailMessage();
 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}
	
	 public static void main( String[] args ) {
    	Mail mm = new Mail();
        mm.sendMail("projetobdic3@gmail.com",
    		   "projetobdic3@gmail.com",
    		   "Teste Email", 
    		   "Teste Envio de Email Projeto BDIC3!");
    }

}
