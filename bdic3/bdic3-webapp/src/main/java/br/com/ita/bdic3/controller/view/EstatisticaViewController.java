package br.com.ita.bdic3.controller.view;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.RuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/estatistica")
public class EstatisticaViewController {

	private static final String VIEW_CONTESTACAO = "view.estatistica";

	@RequestMapping(method = RequestMethod.GET)
	public String validacao(Model model) {
		System.out.println("CHAMA CHAMA CHAMOU");
			
		return VIEW_CONTESTACAO;
	}



	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// public String products(@PathVariable("id") String id, Model model) {
	// Produto produto = produtoService.findById(Long.parseLong(id));
	// model.addAttribute("pedidoVO", new PedidoVO());
	// model.addAttribute("produto", produto);
	// return VIEW_COMPRA;
	// }

}