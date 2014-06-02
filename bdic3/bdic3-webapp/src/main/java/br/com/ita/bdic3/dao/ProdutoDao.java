package br.com.ita.bdic3.dao;

import org.springframework.stereotype.Repository;

import br.com.ita.bdic3.entity.Produto;

/**
 * @author Paulo Vitor Faria Fortes Rezende
 * 
 */
@Repository("produtoDao")
public class ProdutoDao extends GenericDao<Long, Produto> {

	public ProdutoDao() {
		super(Produto.class);
	}
	
}
