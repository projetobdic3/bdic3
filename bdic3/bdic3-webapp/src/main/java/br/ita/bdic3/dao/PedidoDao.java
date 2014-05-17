package br.ita.bdic3.dao;

import org.springframework.stereotype.Repository;

import br.ita.bdic3.entity.Pedido;

/**
 * @author Paulo Vitor Faria Fortes Rezende
 * 
 */
@Repository("pedidoDao")
public class PedidoDao extends GenericDao<Long, Pedido> {

	public PedidoDao() {
		super(Pedido.class);
	}
	
}
