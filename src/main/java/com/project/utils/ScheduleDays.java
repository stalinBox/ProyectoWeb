package com.project.utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ScheduleDays implements Serializable {

	private static final long serialVersionUID = 1L;

	// ***************RECORRER DIAS EN EL CALENDAR*************
	public Calendar nextDayExtras(Calendar a) {
		a.set(Calendar.AM_PM, Calendar.PM);
		a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
		return a;
	}

	public Calendar nextDay(Calendar a) {
		a.set(Calendar.AM_PM, Calendar.PM);
		a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
		return a;
	}

	public Calendar nextDay2(Calendar a) {
		a.set(Calendar.AM_PM, Calendar.PM);
		a.set(Calendar.DATE, a.get(Calendar.DATE) + 2);
		return a;
	}

	public Calendar prevDay(Calendar a) {
		a.set(Calendar.AM_PM, Calendar.PM);
		a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
		return a;
	}

	public Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	private Calendar today() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar;
	}

	public Date CalendarToDay(Calendar a) {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE));

		return t.getTime();
	}
}
