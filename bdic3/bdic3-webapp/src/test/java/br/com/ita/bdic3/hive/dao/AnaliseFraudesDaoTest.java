package br.com.ita.bdic3.hive.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.ita.bdic3.vo.SuspeitaFraudeVO;

public class AnaliseFraudesDaoTest {
	
	
	@Test
	@Ignore
	public void test() throws SQLException {
		AnaliseFraudesDao a = new AnaliseFraudesDao();
		a.fraudeLocalizacao();
		List<SuspeitaFraudeVO> transacoesSuspeitas = a.getTransacoesSuspeitas();
		System.out.println(transacoesSuspeitas.toString());
	}

}
