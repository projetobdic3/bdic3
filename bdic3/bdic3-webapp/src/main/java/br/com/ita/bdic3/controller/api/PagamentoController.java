package br.com.ita.bdic3.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ita.bdic3.dao.PagamentoDao;
import br.com.ita.bdic3.entity.Pagamento;

@Controller
@RequestMapping("/api/transacoes")
public class PagamentoController extends GenericController<Pagamento>{

	public PagamentoController() {
		super(Pagamento.class);
	}

	@Autowired
	private PagamentoDao pagamentoDao;
	
	public String create(Pagamento pagamento){
		
		
		return "sucesso";
		
	}
}
