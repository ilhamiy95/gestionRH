package com.example.parking.helper;

import java.util.Calendar;
import java.util.Date;

public class DateDiff {

    public static int numberOfDaysExcludingWeekend(Date dateDebut, Date dateFin) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(dateDebut);
        cal2.setTime(dateFin);

        int numberOfDays = 0;
        while (cal1.before(cal2) || cal1.equals(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
            }
            cal1.add(Calendar.DATE, 1);
        }
        return numberOfDays;
    }
}
