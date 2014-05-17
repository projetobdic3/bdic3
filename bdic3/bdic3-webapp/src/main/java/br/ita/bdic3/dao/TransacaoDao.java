package br.ita.bdic3.dao;

import org.springframework.stereotype.Repository;

import br.ita.bdic3.entity.Transacao;

/**
 * @author Paulo Vitor Faria Fortes Rezende
 * 
 */
@Repository("transacaoDao")
public class TransacaoDao extends GenericDao<Long, Transacao> {

	public TransacaoDao() {
		super(Transacao.class);
	}

}
