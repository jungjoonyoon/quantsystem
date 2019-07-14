package com.themachine.quantsystem.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * @param date
	 * @return
	 */
	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		return toCalendar(date).get(Calendar.DATE);
	}
	
}
