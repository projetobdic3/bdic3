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

	/* http://localhost:8080/bdic3/helloworld/bdic3 */
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody String hello(@PathVariable String name) {
		return "Hello World! " + name;
	}

	/* http://localhost:8080/bdic3/helloworld/transacao/32 */
	@RequestMapping(value = "/transacao/{id}", method = RequestMethod.GET)
	public @ResponseBody String efetuarTransacao(@PathVariable String id) {
		Transacao transacao = new Transacao();
		transacao.setId(Integer.parseInt(id));
		return transacao.toString();
	}

}
