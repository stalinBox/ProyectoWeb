package com.project.utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ScheduleDays implements Serializable {

	private static final long serialVersionUID = 1L;

	// ***************RECORRER DIAS EN EL CALENDAR*************
	@SuppressWarnings("deprecation")
	public Calendar nextDayExtras(Calendar a) {
		if (a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
			nextDayExtras(a);
		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
			a.set(Calendar.HOUR, 8);
		}
		return a;
	}

	@SuppressWarnings("deprecation")
	public Calendar nextDay(Calendar a) {
		if (a.getTime().getDay() == 5 || a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
			nextDay(a);
		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
			a.set(Calendar.HOUR, 8);
		}
		return a;
	}

	@SuppressWarnings("deprecation")
	public Calendar prevDayApa(Calendar a) {
		if (a.getTime().getDay() == 5 || a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
			prevDayApa(a);
		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
			a.set(Calendar.HOUR, 8);
		}
		return a;
	}

	@SuppressWarnings("deprecation")
	public Calendar prevDayTrq(Calendar a) {
		if (a.getTime().getDay() == 5 || a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
			prevDayTrq(a);

		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 2);
			a.set(Calendar.HOUR, 8);
		}
		return a;
	}

	public Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}
