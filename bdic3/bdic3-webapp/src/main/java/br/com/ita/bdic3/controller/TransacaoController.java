package br.com.ita.bdic3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ita.bdic3.entity.Transacao;
import br.com.ita.bdic3.exception.APIException;
import br.com.ita.bdic3.service.TransacaoService;

@Controller
@RequestMapping("/api/transacoes")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;
	
	/** http://localhost:8080/bdic3/api/transacoes/transacao/1 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Transacao transacoes(@PathVariable Long id) throws APIException {
		return transacaoService.findById(id);
	}
	
	/** http://localhost:8080/bdic3/api/transacoes/transacao */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Transacao> transacoes() throws APIException {
		return transacaoService.findAll();
	}
}