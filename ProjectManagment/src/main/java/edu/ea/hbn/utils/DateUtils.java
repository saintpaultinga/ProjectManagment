package edu.ea.hbn.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.zip.DataFormatException;

public class DateUtils {
	
	public static Date convertStringToDate(String s){
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
				Locale.US);
		Date date =null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String convertDateToString(Date d){
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
				Locale.US);
		
		return  df.format(d);
	}

}
