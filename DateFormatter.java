package com.faikturan.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.faikturan.enums.PatternDate;

public class DateFormatter {

	public String getSystemDate(PatternDate pattern){
		SimpleDateFormat formatter = new SimpleDateFormat(pattern.getValue());
		Date date = new Date();
		String formattedDate = formatter.format(date);	
		return formattedDate;
		
	}
}
