package com.scg.util;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/27/13
 * Time: 8:00 AM
 *
 * Encapsulates a range of two dates, inclusive of the start date and end date.
 */
@SuppressWarnings({"serial", "unchecked"})
public class DateRange implements Serializable {

    private Calendar startCal;
    private Calendar endCal;

    private Date startDate;
    private Date endDate;

    /**
     * Construct a DateRange given two dates.
     * @param startDate
     * @param endDate
     */
    public DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;

        startCal = new GregorianCalendar();
        startCal.setTime(startDate);

        endCal = new GregorianCalendar();
        endCal.setTime(endDate);
    }

    /**
     * Construct a DateRange for the given month.
     * @param month -  the month for which this DateRange should be constructed. This integer constant should be
     *              obtained from the java.util.Calendar class by month name, e.g. Calendar.JANUARY.
     * @param year - the calendar year
     */
    public DateRange(int month, int year) {
        startCal = new GregorianCalendar();
        startCal.set(Calendar.MONTH, month);
        startCal.set(Calendar.YEAR, year);
        startCal.set(Calendar.DAY_OF_MONTH, startCal.getActualMinimum(Calendar.DAY_OF_MONTH));
        startCal.set(Calendar.HOUR_OF_DAY, startCal.getActualMinimum(Calendar.HOUR_OF_DAY));
        startCal.set(Calendar.MINUTE, startCal.getActualMinimum(Calendar.MINUTE));
        startCal.set(Calendar.SECOND, startCal.getActualMinimum(Calendar.SECOND));
        startCal.set(Calendar.MILLISECOND, startCal.getActualMinimum(Calendar.MILLISECOND));
        startDate = startCal.getTime();

        endCal = new GregorianCalendar();
        endCal.setTime(startCal.getTime());
        endCal.add(Calendar.MONTH, 1);
        endCal.add(Calendar.MILLISECOND, -1);
        endDate = endCal.getTime();
    }

    /**
     * Construct a DateRange given two date strings in the correct format.
     * @param start - String representing the start date, of the form MM/dd/yyyy.
     * @param end - String representing the end date, of the form MM/dd/yyyy.
     */
    public DateRange(String start, String end) {
        String[] tokens = start.split("/");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Start and end parameters must be in the form 'MM/dd/yyyy'");
        }
        int year = Integer.parseInt(tokens[tokens.length - 1]);
        int day = Integer.parseInt(tokens[tokens.length - 2]);
        int month = Integer.parseInt(tokens[tokens.length - 3]);
        startCal = new GregorianCalendar(year, month - 1, day);
        startDate = startCal.getTime();

        tokens = end.split("/");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Start and end parameters must be in the form 'MM/dd/yyyy'");
        }
        year = Integer.parseInt(tokens[tokens.length - 1]);
        day = Integer.parseInt(tokens[tokens.length - 2]);
        month = Integer.parseInt(tokens[tokens.length - 3]);
        endCal = new GregorianCalendar();
        endCal.set(year, month - 1, day,
                endCal.getMaximum(Calendar.HOUR_OF_DAY),
                endCal.getMaximum(Calendar.MINUTE),
                endCal.getMaximum(Calendar.SECOND));

        endDate = endCal.getTime();
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /**
     *  Returns true if the specified date is within the range start date <= date <= end date.
     * @param date - the date to check for being within this DateRange.
     * @return - true if the specified date is within this DateRange.
     */
    public boolean isInRange(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        boolean inRange = (cal.after(startCal) && cal.before(endCal)) ||
                cal.getTime().equals(startCal.getTime()) ||
                cal.equals(endCal);
        return inRange;
    }
}
