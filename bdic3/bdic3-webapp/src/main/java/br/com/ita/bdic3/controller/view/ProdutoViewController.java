package br.com.ita.bdic3.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ita.bdic3.entity.Pedido;
import br.com.ita.bdic3.entity.Produto;
import br.com.ita.bdic3.service.PedidoService;
import br.com.ita.bdic3.service.ProdutoService;

@Controller
@RequestMapping("/product")
public class ProdutoViewController {
	
	private static final String VIEW_LISTAGEM = "view.product";
	private static final String VIEW_COMPRA = "view.compra";
	private static final String VIEW_SUCESSO = "view.sucesso";
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String products(Model model) {
		List<Produto> produtos = produtoService.findAll();
		model.addAttribute("produtos", produtos);
        return VIEW_LISTAGEM;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String products(@PathVariable("id") String id, Model model) {
		Produto produto = produtoService.findById(Long.parseLong(id));
		model.addAttribute("produto", produto);
        return VIEW_COMPRA;
	}
	
	@RequestMapping(value = "/comprar", method = RequestMethod.POST)
	public String comprar(@ModelAttribute("pedido") Pedido pedido) {
		pedidoService.save(pedido);
        return VIEW_SUCESSO;
	}

}