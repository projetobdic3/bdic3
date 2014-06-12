package br.com.ita.bdic3.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.ita.bdic3.entity.Midia;
import br.com.ita.bdic3.dao.GenericDao;

/**
 * @author Paulo Vitor Faria Fortes Rezende
 * 
 */
@Repository("midiaDao")
public class MidiaDao extends GenericDao<Long, Midia> {

	public MidiaDao() {
		super(Midia.class);
	}

	public Midia findByClienteENumero(Long idCliente, String numeroCartao) {
		String sql = "FROM Midia WHERE numero = :numero AND cliente.id = :cliente";
		
		Query query = getSession().createQuery(sql);
		query.setParameter("numero", numeroCartao);
		query.setParameter("cliente", idCliente);
		
		return (Midia) query.uniqueResult();
	}

}
