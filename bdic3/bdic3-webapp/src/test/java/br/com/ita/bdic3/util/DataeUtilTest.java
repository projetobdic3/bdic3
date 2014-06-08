package br.com.ita.bdic3.util;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

public class DataeUtilTest {
	
	
	@Test
	public void deveRetornarDataNoFormatoHive(){
		assertEquals("2013-02-12",DateUtil.dateToHive("12/02/2013"));
	}
	@Test
	public void deveRetornarDataNoFormatoString(){
		DateTime dataDateTime = new DateTime(2013,02,12,01,32,59); 
		assertEquals("12/02/2013 01:32:59",DateUtil.datateTimeToString(dataDateTime));
	}

}
