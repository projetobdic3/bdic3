package br.ita.bdic3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ita.bdic3.model.Transacao;

@Controller
@RequestMapping("/helloworld")
public class HelloWorld {
	//http://localhost:8080/bdic3/helloworld/Alguma coisa
	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody String hello(@PathVariable String name){
		
		return "Hello World! " + name;
	}
	
	//http://localhost:8080/bdic3/helloworld/transacao/32
	@RequestMapping(value="/transacao/{valor}", method = RequestMethod.GET)
	public @ResponseBody Transacao fazerTrasacao(@PathVariable String valor){
		
		Transacao transacao = new Transacao();
		transacao.setAnoValidade("2014-01-01");
		transacao.setBandeira("Visa");
		transacao.setCodigoSeguranca("4321");
		transacao.setId(1);
		
		return transacao;
	}

}
