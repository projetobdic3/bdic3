package br.com.ita.bdic3.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PesquisaHiveVOTest {
	
	private PesquisaHiveVO pesquisaHiveVO;
	
	@Before
	public void init(){
		pesquisaHiveVO = new PesquisaHiveVO();
	}
	@Test
	public void deveRetornarDataInicialCorreta(){
		pesquisaHiveVO.setDataInicial("01/02/2013");
		assertEquals("2013-02-01", pesquisaHiveVO.getDataIncialConvertida());
	}
	@Test(expected=IllegalArgumentException.class)
	public void deveRetornarErroAoConverterDataInicial(){
		pesquisaHiveVO.setDataInicial("01-02-2013");
		pesquisaHiveVO.getDataIncialConvertida();
	}
	@Test
	public void deveRetornarDataFinallCorreta(){
		pesquisaHiveVO.setDataFinal("09/04/2013");
		assertEquals("2013-04-09", pesquisaHiveVO.getDataFinalConvertida());
	}
	@Test(expected=IllegalArgumentException.class)
	public void deveRetornarErroAoConverterDataFinall(){
		pesquisaHiveVO.setDataFinal("01-02-2013");
		pesquisaHiveVO.getDataFinalConvertida();
	}

}
