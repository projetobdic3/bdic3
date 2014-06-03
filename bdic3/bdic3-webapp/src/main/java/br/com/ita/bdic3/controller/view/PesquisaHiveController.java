package br.com.ita.bdic3.controller.view;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ita.bdic3.exception.APIException;
import br.com.ita.bdic3.hive.dao.PesquisaHiveTestDao;


@Controller
@RequestMapping("/api/hive")
public class PesquisaHiveController {
	
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

}
