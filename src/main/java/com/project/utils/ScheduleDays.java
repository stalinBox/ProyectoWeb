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
		}
		return a;
	}

	public Calendar nextDay(Calendar a) {
		int b = a.get(Calendar.DAY_OF_WEEK);
		// if ((b == 7) || (b == 1)) {
		// a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
		// nextDay(a);
		// } else {
		// a.set(Calendar.AM_PM, Calendar.PM);
		// a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
		// }

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
		// if (b == 7) {
		// a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
		// prevDay(a);

		// } else if (b == 1) {
		// a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
		// prevDay(a);

		// }
		// else {
		a.set(Calendar.AM_PM, Calendar.PM);
		a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
		// }
		return a;
	}

	public Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}
