package br.com.ita.bdic3.testes.us08;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Ignore;
import org.junit.Test;

public class DateTimeTest {

	@Test
	@Ignore
	public void testConverterStringEmData() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

		DateTime dt1 = formatter.parseDateTime("30/05/2013");
		DateTime dt2 = formatter.parseDateTime("26/05/2013");

		assertEquals(4, Days.daysBetween(dt2, dt1).getDays());
	}

	@Test
	@Ignore
	public void testConverterStringEmTempo() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");

		DateTime dt1 = formatter.parseDateTime("10:00:00");
		DateTime dt2 = formatter.parseDateTime("09:00:00");

		assertEquals(1, Hours.hoursBetween(dt2, dt1).getHours());
	}

	@Test
	@Ignore
	public void testConverterStringEmDateTime() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

		DateTime dt1 = formatter.parseDateTime("28/04/2014 10:00:00");
		DateTime dt2 = formatter.parseDateTime("27/04/2014 10:00:00");

		assertEquals(24, Hours.hoursBetween(dt2, dt1).getHours());
	}

}
