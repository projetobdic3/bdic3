package br.com.ita.bdic3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.dao.ClienteDao;
import br.com.ita.bdic3.entity.Cliente;
import br.com.ita.bdic3.vo.ContestacaoVO;

@Component
public class ContestacaoService {
	
	@Autowired
	private ClienteDao clienteDao;

	public boolean validarCliente(ContestacaoVO contestacaoVO) {
		Cliente cliente = clienteDao.findByNomeAndCpf(contestacaoVO.getNomeCliente(), contestacaoVO.getCpfCliente());
		if(cliente != null){
			return true;
		} else {
			return false;
		}
	}

}
