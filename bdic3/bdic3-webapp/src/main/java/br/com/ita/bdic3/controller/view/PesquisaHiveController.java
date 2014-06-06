package br.com.ita.bdic3.controller.view;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ita.bdic3.exception.APIException;
import br.com.ita.bdic3.hive.dao.PesquisaHiveTestDao;
import br.com.ita.bdic3.vo.PesquisaHiveVO;


@Controller
@RequestMapping("/hive")
public class PesquisaHiveController {
	private static final String VIEW_FORM = "view.pesquisaHive";
	
	@Autowired
	private PesquisaHiveTestDao pesquisaHiveTestDao;
	
	@RequestMapping(value = "/pesquisar/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String pesquisar(@PathVariable Long id) throws APIException {
		try {
			pesquisaHiveTestDao.testConectar();
		} catch (SQLException e) {
			return "erro";
		}
		return "ok";
	}
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model){
		PesquisaHiveVO pesquisaHiveVO = new PesquisaHiveVO();
		List<String> localidades = pesquisaHiveTestDao.buscarLocalidades();
		model.addAttribute("pesquisaHiveVO", pesquisaHiveVO);
		model.addAttribute("localidades", localidades);
		return VIEW_FORM;
	}

}
