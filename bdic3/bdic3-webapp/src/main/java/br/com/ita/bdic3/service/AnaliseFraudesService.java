package br.com.ita.bdic3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import br.com.ita.bdic3.dao.RelatorioFraudeDao;
import br.com.ita.bdic3.entity.RelatorioFraude;
import br.com.ita.bdic3.entity.SuspeitaFraudeVO;
import br.com.ita.bdic3.hive.dao.AnaliseFraudesDao;
import br.com.ita.bdic3.util.Mail;
import br.com.ita.bdic3.vo.PesquisaHiveVO;

@Service
public class AnaliseFraudesService {
	
	@Autowired
	private AnaliseFraudesDao analiseFraudesDao;
	
	@Autowired
	private RelatorioFraudeDao relatorioFraudeDao;
	
	@Autowired
	private Mail mail;
	private List<SuspeitaFraudeVO>  fraudeVOs;
	AtomicBoolean working = new AtomicBoolean(false);
	
	public List<SuspeitaFraudeVO> buscarSuspeitasDeFraudes(PesquisaHiveVO pesquisaHiveVO){
		List<SuspeitaFraudeVO> suspeitasFraudes = new ArrayList<SuspeitaFraudeVO>();
		try{
			suspeitasFraudes = analiseFraudesDao.fraudeLocalizacao(pesquisaHiveVO);
			RelatorioFraude relatorioFraude =  new RelatorioFraude();
			relatorioFraude.setSuspeitasFraudes(suspeitasFraudes);
			Long id = relatorioFraudeDao.save(relatorioFraude);
			mail.sendMail("projetobdic3@gmail.com", "Análise de Fraude", "Código do relatorio: "+id);
		}catch(Exception e){
			mail.sendMail("projetobdic3@gmail.com", "Análise de Fraude ERRO", "Erro ao gerar relatorio" + e.getMessage() );
		}
		
		return suspeitasFraudes;
	}
	
	@Async
	public void buscarSuspeitasDeFraudesSlow(PesquisaHiveVO pesquisaHiveVO){
		if(working.compareAndSet(false, true)) {
			fraudeVOs = analiseFraudesDao.fraudeLocalizacao(pesquisaHiveVO);
		
			working.set(false);
		}
	}
	public List<SuspeitaFraudeVO> getFraudeVOs() {
		return fraudeVOs;
	}

}
