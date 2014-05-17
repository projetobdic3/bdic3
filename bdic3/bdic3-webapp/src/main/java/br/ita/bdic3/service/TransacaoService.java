package br.ita.bdic3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ita.bdic3.dao.TransacaoDao;
import br.ita.bdic3.entity.Transacao;

@Component
public class TransacaoService {

	@Autowired
	private TransacaoDao transacaoDao;
	
	public void save(Transacao transacao) {
		transacaoDao.save(transacao);
	}
	
	public void update(Transacao transacao) {
		transacaoDao.update(transacao);
	}
	
	public void delete(Transacao transacao) {
		transacaoDao.delete(transacao);
	}
	
	public void delete(Long id) {
		transacaoDao.delete(id);
	}
	
	public List<Transacao> findAll() {
		return transacaoDao.findAll();
	}
	
	public Transacao findById(Long id) {
		return transacaoDao.findById(id);
	}
}
