package br.com.ita.bdic3.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.junit.Test;

public class DataeUtilTest {
	
	
	@Test
	public void deveRetornarDataNoFormatoHive(){
		assertEquals("2013-02-12 00:00:00",DateUtil.dateToHive("12/02/2013"));
	}
	@Test
	public void deveRetornarDataNoFormatoString(){
		DateTime dataDateTime = new DateTime(2013,02,12,01,32,59); 
		assertEquals("12/02/2013 01:32:59",DateUtil.datateTimeToString(dataDateTime));
	}
	@Test
	public void deveRetornarDataCalendarNoFormatoString(){
		Calendar dataDateTime = new GregorianCalendar(2013,02,12,01,32,59); 
		String calendarToString = DateUtil.calendarToString(dataDateTime);
		System.out.println(calendarToString);
		assertEquals("12/03/2013",calendarToString);
	}

}
