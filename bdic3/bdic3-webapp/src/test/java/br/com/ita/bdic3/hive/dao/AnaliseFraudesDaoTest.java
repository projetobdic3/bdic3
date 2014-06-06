package br.com.ita.bdic3.hive.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.ita.bdic3.vo.SuspeitaFraudeVO;

public class AnaliseFraudesDaoTest {
	
	
	@Test
	@Ignore
	public void test() throws SQLException {
		AnaliseFraudesDao analiseFraudesDao = new AnaliseFraudesDao();
		List<SuspeitaFraudeVO> transacoesSuspeitas = analiseFraudesDao.fraudeLocalizacao();;
		System.out.println(transacoesSuspeitas.toString());
	}

}
