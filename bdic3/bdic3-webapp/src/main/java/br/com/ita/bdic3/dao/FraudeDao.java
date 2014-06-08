package br.com.ita.bdic3.dao;

import org.springframework.stereotype.Repository;

import br.com.ita.bdic3.entity.Fraude;

@Repository("fraudeDao")
public class FraudeDao extends GenericDao<Long, Fraude>{

	public FraudeDao() {
		super(Fraude.class);
	}
}
