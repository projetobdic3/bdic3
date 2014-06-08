package br.com.ita.bdic3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.dao.ClienteDao;
import br.com.ita.bdic3.entity.Cliente;
import br.com.ita.bdic3.vo.ContestacaoVO;
import br.ita.bdic3.hive.dao.ContestacaoDao;

@Component
public class ContestacaoService {
	
	@Autowired
	private ClienteDao clienteDao;

	private ContestacaoDao contestacaoDao = new ContestacaoDao();
	
	public boolean validarCliente(ContestacaoVO contestacaoVO) {
		Cliente cliente = clienteDao.findByNomeAndCpf(contestacaoVO.getNomeCliente(), contestacaoVO.getCpfCliente());
		if(cliente != null){
			return true;
		} else {
			return false;
		}
	}

	public boolean validarTransacao(ContestacaoVO contestacaoVO) {
		return contestacaoDao.findTransacaoByValorOrData(contestacaoVO);
	}

	public List<ContestacaoVO> filtrar(ContestacaoVO contestacaoVO) {
		return contestacaoDao.findTransacaoByIntervaloDataAndIntervaloValor(contestacaoVO);
	}

}
