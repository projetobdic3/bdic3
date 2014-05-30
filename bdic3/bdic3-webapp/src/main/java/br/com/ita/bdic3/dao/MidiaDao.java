package br.com.ita.bdic3.dao;

import org.springframework.stereotype.Repository;

import br.com.ita.bdic3.entity.Midia;

/**
 * @author Paulo Vitor Faria Fortes Rezende
 * 
 */
@Repository("midiaDao")
public class MidiaDao extends GenericDao<Long, Midia> {

	public MidiaDao() {
		super(Midia.class);
	}

}
