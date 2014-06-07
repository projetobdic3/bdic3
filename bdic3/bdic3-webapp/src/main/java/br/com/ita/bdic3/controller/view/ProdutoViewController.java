package br.com.ita.bdic3.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ita.bdic3.entity.Produto;
import br.com.ita.bdic3.fixture.LocalidadeFixture;
import br.com.ita.bdic3.service.PedidoService;
import br.com.ita.bdic3.service.ProdutoService;
import br.com.ita.bdic3.vo.CidadeVO;
import br.com.ita.bdic3.vo.PedidoVO;

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

	@RequestMapping(value = "/listar-cidades/{sigla}", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody List<CidadeVO> listarCidadesPorEstado(@PathVariable("sigla") String sigla, Model model) {
        return LocalidadeFixture.getCidadesPorSiglaEstado(sigla);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String products(@PathVariable("id") String idProduto, Model model) {
		model.addAttribute("idProduto", Long.parseLong(idProduto));
		model.addAttribute("estados", LocalidadeFixture.getEstadosComCidades());
		model.addAttribute("cidades", LocalidadeFixture.getTodasCidades());
		model.addAttribute("pedidoVO", new PedidoVO());
        return VIEW_COMPRA;
	}
	
	@RequestMapping(value = "/comprar", method = RequestMethod.POST)
	public String comprar(@ModelAttribute("pedidoVO") PedidoVO pedidoVO, Model model) throws Exception {
		try {
			pedidoService.efetuarCompra(pedidoVO);
			return VIEW_SUCESSO;
		} catch (Exception e) {
			model.addAttribute("erro", e.getMessage());
			return VIEW_COMPRA;
		}
	}

}