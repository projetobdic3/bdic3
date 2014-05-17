package br.ita.bdic3.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ita.bdic3.entity.Produto;
import br.ita.bdic3.service.ProdutoService;

@Controller
@RequestMapping("/product")
public class ProdutoViewController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String produtos(Model model) {
		List<Produto> produtos = produtoService.findAll();
		model.addAttribute("produtos", produtos);
        return "view.product";
	}
}
