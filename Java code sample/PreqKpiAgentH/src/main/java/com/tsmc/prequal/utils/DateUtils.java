package com.tsmc.prequal.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateUtils {

	public static SimpleDateFormat DateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static String toTimeString (LocalDate _inputDate)
	{
		return DateTimeFormat.format(_inputDate); 
	}
	
	public static String nowAsTimeString()
	{
		/*
		// java.util.Date --> java.sql.Date
		java.util.Date now = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(now.getTime());

		// java.sql.Date --> java.util.Date
		java.util.Date utilDate = new java.util.Date();
		utilDate.setTime(sqlDate.getTime());
		//*/
		
//		LocalDate date = LocalDate.now();
		return toTimeString(LocalDate.now()); 
	}
	
	public static String toTimeString(Timestamp _inputTimestamp) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String timeStr = df.format(_inputTimestamp); 

		return timeStr; 
	}
	public static String toTimeString(java.util.Date _inputDate) {

		Timestamp ts = new Timestamp(_inputDate.getTime());
		return DateUtils.toTimeString(ts); 
	}
	
	public static String getCurTimeString()
	{
		//System.currentTimeMillis()
		Timestamp curTimestamp= new Timestamp(System.currentTimeMillis()); //Get System-Time
		return DateUtils.toTimeString(curTimestamp); 
	}
	
	public static Timestamp toTimeStamp(String _inTimeString) {
	
		Timestamp respTimestamp = Timestamp.valueOf(_inTimeString); 
		return respTimestamp; 
	}
	

}
