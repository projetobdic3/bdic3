package br.com.ita.bdic3.dao;

import org.springframework.stereotype.Repository;

import br.com.ita.bdic3.entity.Pagamento;
import br.com.ita.bdic3.dao.GenericDao;

@Repository
public class PagamentoDao extends GenericDao<Long, Pagamento>{

	public PagamentoDao() {
		super(Pagamento.class);
	}

}
