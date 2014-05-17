package br.ita.bdic3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ita.bdic3.dao.ProdutoDao;
import br.ita.bdic3.entity.Produto;

@Component
public class ProdutoService {

	@Autowired
	private ProdutoDao produtoDao;
	
	public void save(Produto produto) {
		produtoDao.save(produto);
	}
	
	public void update(Produto produto) {
		produtoDao.update(produto);
	}
	
	public void delete(Produto produto) {
		produtoDao.delete(produto);
	}
	
	public void delete(Long id) {
		produtoDao.delete(id);
	}
	
	public List<Produto> findAll() {
		return produtoDao.findAll();
	}
	
	public Produto findById(Long id) {
		return produtoDao.findById(id);
	}
}
