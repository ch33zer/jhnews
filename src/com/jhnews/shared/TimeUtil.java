package com.jhnews.shared;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
	public static Date getMidnightOf(Date date) {
		// today    
		Calendar midnight = new GregorianCalendar();
		midnight.setTime(date);
		// reset hour, minutes, seconds and millis
		midnight.set(Calendar.HOUR_OF_DAY, 0);
		midnight.set(Calendar.MINUTE, 0);
		midnight.set(Calendar.SECOND, 0);
		midnight.set(Calendar.MILLISECOND, 0);
		return midnight.getTime();
	}
	
	public static Date getMidnightOfTomorrow(Date date) {
		return addOneDay(getMidnightOf(date));
	}
	
	public static Date addOneDay(Date date) {
		Calendar nextDay = new GregorianCalendar();
		nextDay.add(Calendar.DAY_OF_MONTH, 1);
		return nextDay.getTime();
	}
}
