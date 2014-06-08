package br.com.ita.bdic3.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.ita.bdic3.entity.Cliente;

@Repository("clienteDao")
public class ClienteDao extends GenericDao<Long, Cliente> {

	public ClienteDao() {
		super(Cliente.class);
	}

	public Cliente findByNomeAndCpf(String nomeCliente, String cpfCliente) {
		String sql = "FROM Cliente WHERE cli_nome = :nome AND cli_cpf = :cpf";

		Query query = getSession().createQuery(sql);
		query.setParameter("nome", nomeCliente);
		query.setParameter("cpf", cpfCliente);

		System.out.println(nomeCliente + " " + cpfCliente);
		
		return (Cliente) query.uniqueResult();
	}
}
