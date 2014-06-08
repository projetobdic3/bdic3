package br.com.ita.bdic3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.dao.FraudeDao;
import br.com.ita.bdic3.entity.Fraude;
import br.com.ita.bdic3.entity.FraudeTipo;
import br.com.ita.bdic3.entity.Pedido;
import br.com.ita.bdic3.util.Mail;

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
	
	public void salvarFraude(Pedido pedido, FraudeTipo tipo) {
		Fraude fraude = new Fraude();
		
		fraude.setTipo(tipo);
		fraude.setTransacao(pedido.getTransacao());
		fraude.setNome("FRAUDE");
		fraude.setFormaDeteccao("VALIDACAO");
		
		fraudeDao.save(fraude);
	}
}
