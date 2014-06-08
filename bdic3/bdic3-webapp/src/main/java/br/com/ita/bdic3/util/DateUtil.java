package br.com.ita.bdic3.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {

	public static final SimpleDateFormat FORMATO_DATA = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat FORMATO_DATA_BANCAO = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public static String dateToHive(String data){
		return formatarEmDiaMesAnoToBacno(stringToCalendar(data));
	}
	public static String formatarEmDiaMesAnoToBacno(Calendar data) {
		return data != null? FORMATO_DATA_BANCAO.format(data.getTime()) : null;
	}
	public static Calendar stringToCalendar(String data){
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(FORMATO_DATA.parse(data));
			return cal;
			
		}catch (ParseException e) {			
				throw new IllegalArgumentException( e );
		}
	}
	public static String stringToDateTime(DateTime data){
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		return formatter.print(data.getMillis());
	}
}
