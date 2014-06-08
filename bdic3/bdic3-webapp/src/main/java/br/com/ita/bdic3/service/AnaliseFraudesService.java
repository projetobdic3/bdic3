package br.com.ita.bdic3.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import br.com.ita.bdic3.hive.dao.AnaliseFraudesDao;
import br.com.ita.bdic3.vo.PesquisaHiveVO;
import br.com.ita.bdic3.vo.SuspeitaFraudeVO;

@Service
public class AnaliseFraudesService {
	
	@Autowired
	private AnaliseFraudesDao analiseFraudesDao;
//	private List<SuspeitaFraudeVO>  fraudeVOs;
//	AtomicBoolean working = new AtomicBoolean(false);
	
	public List<SuspeitaFraudeVO> buscarSuspeitasDeFraudes(PesquisaHiveVO pesquisaHiveVO){
		
		List<SuspeitaFraudeVO> suspeitasFraudes = analiseFraudesDao.fraudeLocalizacao(pesquisaHiveVO);
		
		return suspeitasFraudes;
	}
//	@Async
//	public Future<List<SuspeitaFraudeVO>> buscarSuspeitasDeFraudesSlow(PesquisaHiveVO pesquisaHiveVO){
//		if(working.compareAndSet(false, true)) {
//			fraudeVOs = analiseFraudesDao.fraudeLocalizacao(pesquisaHiveVO);
//		
//			working.set(false);
//		}
//		
//		return new  AsyncResult<List<SuspeitaFraudeVO>>(fraudeVOs);
//	}
//	public List<SuspeitaFraudeVO> getFraudeVOs() {
//		return fraudeVOs;
//	}

}
