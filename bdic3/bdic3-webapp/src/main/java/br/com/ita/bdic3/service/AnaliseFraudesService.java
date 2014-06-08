package br.com.ita.bdic3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ita.bdic3.hive.dao.AnaliseFraudesDao;
import br.com.ita.bdic3.vo.PesquisaHiveVO;
import br.com.ita.bdic3.vo.SuspeitaFraudeVO;

@Service
public class AnaliseFraudesService {
	
	@Autowired
	private AnaliseFraudesDao analiseFraudesDao;
	
	public List<SuspeitaFraudeVO> buscarSuspeitasDeFraudes(PesquisaHiveVO pesquisaHiveVO){
		
		List<SuspeitaFraudeVO> suspeitasFraudes = analiseFraudesDao.fraudeLocalizacao(pesquisaHiveVO);
		
		return suspeitasFraudes;
	}

}
