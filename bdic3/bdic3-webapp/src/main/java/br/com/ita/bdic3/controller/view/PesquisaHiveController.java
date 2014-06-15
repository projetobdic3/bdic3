package br.com.ita.bdic3.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ita.bdic3.dao.RelatorioFraudeDao;
import br.com.ita.bdic3.entity.RelatorioFraude;
import br.com.ita.bdic3.hive.dao.PesquisaHiveDao;
import br.com.ita.bdic3.service.AnaliseFraudesService;
import br.com.ita.bdic3.vo.PesquisaHiveVO;


@Controller
@RequestMapping("/hive")
public class PesquisaHiveController {
	
	private static final String VIEW_FORM = "view.pesquisaHive";
	private static final String VIEW_RESULTADO = "view.resultadoSuspeitasFraudes";
	private static final String VIEW_RELATORIO = "view.relatorioHive";
	
	@Autowired
	private AnaliseFraudesService analiseFraudesService;
	
	@Autowired
	private PesquisaHiveDao pesquisaHiveDao;
	
	@Autowired
	private RelatorioFraudeDao relatorioFraudeDao;
	
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model){
		PesquisaHiveVO pesquisaHiveVO = new PesquisaHiveVO();
		List<String> localidades = pesquisaHiveDao.buscarLocalidades();
		model.addAttribute("pesquisaHive", pesquisaHiveVO);
		model.addAttribute("localidades", localidades);
		return VIEW_FORM;
	}
	
	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public void pesquisarFraudesSlow(Model model, PesquisaHiveVO pesquisaHiveVO){
			analiseFraudesService.buscarSuspeitasDeFraudes(pesquisaHiveVO);
	}
	
	@RequestMapping(value = "/relatorio", method = RequestMethod.GET)
	public String formRelatorio(){
		return VIEW_RELATORIO;
	}
	
	@RequestMapping(value = "/pesquisarRelatorio", method = RequestMethod.POST)
	public String pesquisarRelatorio(Model model,@RequestParam("id") Long id){
		try{
			 
			RelatorioFraude relatorio = relatorioFraudeDao.findById(id);
			model.addAttribute("suspeitasFraude", relatorio.getSuspeitasFraudes());
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("mensagemErro", "Dados Invalidos!"+e.getMessage());
			return "";
		}
		return VIEW_RESULTADO;
	}

	
}
