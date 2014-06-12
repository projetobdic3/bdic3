package br.com.ita.bdic3.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.dao.FraudeDao;
import br.com.ita.bdic3.entity.Fraude;
import br.com.ita.bdic3.entity.FraudeTipo;
import br.com.ita.bdic3.entity.Pedido;
import br.com.ita.bdic3.util.DateUtil;
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
		//mail.sendMail(Mail.PROJETOBDIC3_EMAIL, "Fraude", "Fraude");
	}

	public void notificarSuspeitaDeFraude(Pedido pedido) {
		salvarFraude(pedido, FraudeTipo.BOXSPLOT);
		//mail.sendMail(Mail.PROJETOBDIC3_EMAIL, "Suspeita de Fraude",
		//		"Suspeita de Fraude");
	}

	public Fraude salvarFraude(Pedido pedido, FraudeTipo tipo) {
		Fraude fraude = new Fraude();

		fraude.setTipo(tipo);
		fraude.setTransacao(pedido.getTransacao());
		fraude.setNome("FRAUDE");
		fraude.setFormaDeteccao("VALIDACAO");

		fraudeDao.save(fraude);
		notificaFraudeMapa(pedido, fraude);
		return fraude;
	}

	// Notifica a aplicação de mapas sobre a fraude identificada usando o
	// serviço Pusher
	public void notificaFraudeMapa(Pedido pedido, Fraude fraude) {
		// TODO Utilizar o objeto Fraude para montar o Json da Fraude, que será
		// enviado ao cliente, conforme exemplo abaixo

		String jsonData = "{\"Latitude\":\""
				+ pedido.getPagamento().getLocalidade().getLatitude()
				+ "\", \"Longitude\":\""
				+ pedido.getPagamento().getLocalidade().getLongitude() + "\","
				+ "\"Nome\":\"Projeto BDIC3\", \"Tipo_Fraude\":\""
				+ fraude.getTipo().name() + "\"," + "\"Deteccao\":\""
				+ fraude.getFormaDeteccao() + "\", \"Data_Deteccao\":\""
				+ DateUtil.calendarToString(Calendar.getInstance()) + "\","
				+ "\"Tipo_Transacao\":\"CARTAO\", \"Valor\":\""
				+ pedido.getValorTotal() + "\"" + "}";
		System.out.println("JASON = " + jsonData);
		Pusher.triggerPush("test_channel", "my_event", jsonData);
	}
}
