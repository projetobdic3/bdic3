package br.com.ita.bdic3.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ita.bdic3.exception.APIException;
import br.com.ita.bdic3.hive.dao.AnaliseFraudesDao;
import br.com.ita.bdic3.hive.dao.PesquisaHiveDao;
import br.com.ita.bdic3.vo.PesquisaHiveVO;
import br.com.ita.bdic3.vo.SuspeitaFraudeVO;


@Controller
@RequestMapping("/hive")
public class PesquisaHiveController {
	private static final String VIEW_FORM = "view.pesquisaHive";
	
	@Autowired
	private AnaliseFraudesDao analiseFraudesDao;
	
	@Autowired
	private PesquisaHiveDao pesquisaHiveDao;
	
	@RequestMapping(value = "/pesquisar/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String pesquisar(@PathVariable Long id) throws APIException {
		/*try {
			//pesquisaHiveTestDao.testConectar();
		} catch (SQLException e) {
			return "erro";
		}*/
		return "ok";
	}
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model){
		PesquisaHiveVO pesquisaHiveVO = new PesquisaHiveVO();
		List<String> localidades = pesquisaHiveDao.buscarLocalidades();
		model.addAttribute("pesquisaHiveVO", pesquisaHiveVO);
		model.addAttribute("localidades", localidades);
		return VIEW_FORM;
	}
	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public String pesquisarFraudes(Model model, PesquisaHiveVO pesquisaHiveVO){
		List<SuspeitaFraudeVO> suspeitasFraudes = analiseFraudesDao.fraudeLocalizacao();
		model.addAttribute("suspeitaFraude", suspeitasFraudes);
		
		return "view.resultadoSuspeitasFraudes";
	}

}
