package com.jhnews.shared;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/** This class provides time related services.
 * @author group 8
 *
 */
public class TimeUtil {
	/** Gets the time of midnight of a given time
	 * @param date the day to get the midnight time for
	 * @return the midnight time
	 */
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
	
	/** Gets the time of midnight tomorrow based on a given time
	 * @param date the given time to find the midnight tomorrow for
	 * @return the time of midnight tomorrow
	 */
	public static Date getMidnightOfTomorrow(Date date) {
		return addOneDay(getMidnightOf(date));
	}
	
	/** Adds one day to the given Date object
	 * @param date the date to add one day to
	 * @return the date of the next day
	 */
	public static Date addOneDay(Date date) {
		Calendar nextDay = new GregorianCalendar();
		nextDay.add(Calendar.DAY_OF_MONTH, 1);
		return nextDay.getTime();
	}
}
