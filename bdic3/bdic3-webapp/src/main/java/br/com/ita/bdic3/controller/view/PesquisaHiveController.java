package br.com.ita.bdic3.controller.view;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ita.bdic3.hive.dao.PesquisaHiveDao;
import br.com.ita.bdic3.service.AnaliseFraudesService;
import br.com.ita.bdic3.vo.PesquisaHiveVO;
import br.com.ita.bdic3.vo.SuspeitaFraudeVO;


@Controller
@RequestMapping("/hive")
public class PesquisaHiveController {
	
	private static final String VIEW_FORM = "view.pesquisaHive";
	private static final String VIEW_RESULTADO = "view.resultadoSuspeitasFraudes";
	
	@Autowired
	private AnaliseFraudesService analiseFraudesService;
	
	@Autowired
	private PesquisaHiveDao pesquisaHiveDao;
	
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model){
		PesquisaHiveVO pesquisaHiveVO = new PesquisaHiveVO();
		List<String> localidades = pesquisaHiveDao.buscarLocalidades();
		model.addAttribute("pesquisaHive", pesquisaHiveVO);
		model.addAttribute("localidades", localidades);
		return VIEW_FORM;
	}
	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public String pesquisarFraudes(Model model, PesquisaHiveVO pesquisaHiveVO){
		try{
			List<SuspeitaFraudeVO> suspeitasFraudes = analiseFraudesService.buscarSuspeitasDeFraudes(pesquisaHiveVO);
			model.addAttribute("suspeitaFraude", suspeitasFraudes);
		}catch(Exception e){
			model.addAttribute("pesquisaHive", pesquisaHiveVO);
			model.addAttribute("localidades", pesquisaHiveDao.buscarLocalidades());
			model.addAttribute("mensagemErro", "Dados Invalidos!"+e.getMessage());
			return VIEW_FORM;
		}
		return VIEW_RESULTADO;
	}
//	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
//	public String pesquisarFraudesSlow(Model model, PesquisaHiveVO pesquisaHiveVO){
//		try{
//			 Future<List<SuspeitaFraudeVO>> suspeitasFraudes = analiseFraudesService.buscarSuspeitasDeFraudesSlow(pesquisaHiveVO);
//			model.addAttribute("suspeitaFraude", suspeitasFraudes);
//		}catch(Exception e){
//			model.addAttribute("pesquisaHive", pesquisaHiveVO);
//			model.addAttribute("localidades", pesquisaHiveDao.buscarLocalidades());
//			model.addAttribute("mensagemErro", "Dados Invalidos!"+e.getMessage());
//			return VIEW_FORM;
//		}
//		return VIEW_RESULTADO;
//	}

}
