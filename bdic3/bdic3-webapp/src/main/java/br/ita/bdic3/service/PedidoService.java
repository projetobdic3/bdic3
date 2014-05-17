package br.ita.bdic3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ita.bdic3.dao.PedidoDao;
import br.ita.bdic3.entity.Pedido;

@Component
public class PedidoService {

	@Autowired
	private PedidoDao pedidoDao;
	
	public void save(Pedido Pedido) {
		pedidoDao.save(Pedido);
	}
	
	public void update(Pedido Pedido) {
		pedidoDao.update(Pedido);
	}
	
	public void delete(Pedido Pedido) {
		pedidoDao.delete(Pedido);
	}
	
	public void delete(Long id) {
		pedidoDao.delete(id);
	}
	
	public List<Pedido> findAll() {
		return pedidoDao.findAll();
	}
	
	public Pedido findById(Long id) {
		return pedidoDao.findById(id);
	}
}
