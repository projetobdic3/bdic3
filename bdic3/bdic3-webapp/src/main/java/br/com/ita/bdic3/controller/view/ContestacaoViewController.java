package br.com.ita.bdic3.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ita.bdic3.service.ContestacaoService;
import br.com.ita.bdic3.vo.ContestacaoVO;

@Controller
@RequestMapping("/contestacao")
public class ContestacaoViewController {

	private static final String VIEW_CONTESTACAO = "view.contestacao";
	private static final String VIEW_CONTESTACAO_FRAUDE = "view.contestacao-fraude";

	@Autowired
	private ContestacaoService contestacaoService;

	@RequestMapping(method = RequestMethod.GET)
	public String validacao(Model model) {
		model.addAttribute("contestacaoVO", new ContestacaoVO());
		return VIEW_CONTESTACAO;
	}

	@RequestMapping(value = "/validar-cliente", method = RequestMethod.POST)
	public String validarCliente(@ModelAttribute("contestacaoVO") ContestacaoVO contestacaoVO, Model model) {
		if (contestacaoService.validarCliente(contestacaoVO)) {
			return VIEW_CONTESTACAO_FRAUDE;
		} else {
			model.addAttribute("mensagemErro", "Dados Invalidos");
			return VIEW_CONTESTACAO;
		}
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// public String products(@PathVariable("id") String id, Model model) {
	// Produto produto = produtoService.findById(Long.parseLong(id));
	// model.addAttribute("pedidoVO", new PedidoVO());
	// model.addAttribute("produto", produto);
	// return VIEW_COMPRA;
	// }

}