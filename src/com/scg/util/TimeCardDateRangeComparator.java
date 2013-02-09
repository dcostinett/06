package com.scg.util;

import com.scg.domain.TimeCard;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/27/13
 * Time: 10:03 AM
 */
@SuppressWarnings({"serial", "unchecked"})
public class TimeCardDateRangeComparator implements Comparator<TimeCard>, Serializable {
    @Override
    public int compare(TimeCard o1, TimeCard o2) {
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(o1.getWeekStartingDay());

        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(o2.getWeekStartingDay());

        return cal1.compareTo(cal2);
    }
}
