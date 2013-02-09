package com.scg.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/21/13
 * Time: 2:25 PM
 *
 * Encapsulates a single billable item to be included in an invoice.
 */
public class InvoiceLineItem {
    private final Date date;
    private final Consultant consultant;
    private final Skill skill;
    private final int hours;


    /**
     *
     * @param date - The date of this line item.
     * @param consultant - Consultant for this line item.
     * @param skill - Skill for this line item.
     * @param hours - Hours for this line item.
     */
    public InvoiceLineItem(Date date, Consultant consultant, Skill skill, int hours) {
        this.date = date;
        this.consultant = consultant;
        this.skill = skill;
        this.hours = hours;
    }

    public Date getDate() {
        return date;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public Skill getSkill() {
        return skill;
    }

    public int getHours() {
        return hours;
    }

    public int getCharge() {
        return skill.getRate() * hours;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        sb.append(String.format("%1$tm/%1$td/%1$tY  %2$-29s  %3$-18s  %4$5d  %5$,10.2f",
                cal, consultant, skill, hours, new Float(getCharge())));

        return sb.toString();
    }
}
