package br.com.ita.bdic3.dao;

import org.springframework.stereotype.Repository;

import br.com.ita.bdic3.entity.RelatorioFraude;

@Repository
public class RelatorioFraudeDao extends  GenericDao<Long, RelatorioFraude>{

	public RelatorioFraudeDao() {
		super(RelatorioFraude.class);
		// TODO Auto-generated constructor stub
	}

}
