package br.com.ita.bdic3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.dao.FraudeDao;
import br.com.ita.bdic3.entity.Fraude;
import br.com.ita.bdic3.entity.FraudeTipo;
import br.com.ita.bdic3.entity.Pedido;
import br.com.ita.bdic3.util.Mail;
import br.com.ita.bdic3.util.Pusher;

@Component
public class FraudeService {

	@Autowired
	private FraudeDao fraudeDao;
	
	@Autowired
	private Mail mail;
	
	public void notificarFraude(Pedido pedido) {
		salvarFraude(pedido, FraudeTipo.BOXSPLOT);
		mail.sendMail("paulovffr@gmail.com", "Fraude", "Fraude");
	}
	
	public void notificarSuspeitaDeFraude(Pedido pedido) {
		salvarFraude(pedido, FraudeTipo.BOXSPLOT);
		mail.sendMail("paulovffr@gmail.com", "Suspeita de Fraude", "Suspeita de Fraude");
	}
	
	public Fraude salvarFraude(Pedido pedido, FraudeTipo tipo) {
		Fraude fraude = new Fraude();
		
		fraude.setTipo(tipo);
		fraude.setTransacao(pedido.getTransacao());
		fraude.setNome("FRAUDE");
		fraude.setFormaDeteccao("VALIDACAO");
		
		fraudeDao.save(fraude);
		
		return fraude;
	}
	
	// Notifica a aplicação de mapas sobre a fraude identificada usando o serviço Pusher
	public void notificaFraudeMapa(Fraude fraude) throws Exception {
		//TODO Utilizar o objeto Fraude para montar o Json da Fraude, que será enviado ao cliente, conforme exemplo abaixo
		String jsonData = "{\"Latitude\":\"10\", \"Longitude\":\"10\","+
		                  "\"Nome\":\"Manoel Pereira de Mello\", \"Tipo_Fraude\":\"ESTELIONATO\","+
		                  "\"Deteccao\":\"AUTOMATICA\", \"Data_Deteccao\":\"18/06/2014\","+
		                  "\"Tipo_Transacao\":\"CARTAO\", \"Data_Deteccao\":\"10.5\""+
		                  "}"; 
		Pusher.triggerPush("test_channel", "my_event", jsonData);	
	}
}
