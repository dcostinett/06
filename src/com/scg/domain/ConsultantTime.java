package com.scg.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/19/13
 * Time: 11:14 AM
 * <p/>
 * A consultants time, maintains date, skill, account and hours data.
 */
@SuppressWarnings({"serial", "unchecked"})
public class ConsultantTime implements Serializable {
    /**
     * The date this instance occurred.
     */
    private Date date;

    /**
     * The account to charge the hours to; either a Client or a NonBillableAccount.
     */
    private Account account;

    /**
     * The skill type.
     */
    private Skill skill;

    /**
     * The number of hours, which must be positive.
     */
    private int hours;


    public ConsultantTime(final Date date, final Account account, final Skill skill, final int hours) {
        this.date = new Date(date.getTime());
        this.account = account;
        this.skill = skill;

        setHours(hours);
    }

    public Date getDate() {
        return (date == null) ? (Date)null : new Date(date.getTime());
    }

    /**
     * @param date - New value of the property date.
     */
    public void setDate(Date date) {
        this.date = (date != null) ? (new Date(date.getTime())) : (Date)null;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Skill getSkill() {
        return skill;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) throws IllegalArgumentException {

        if (hours <= 0) {
            throw new IllegalArgumentException("Hours must be positive.");
        }
        this.hours = hours;
    }

    public boolean isBillable() {
        return account.isBillable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsultantTime)) return false;

        ConsultantTime that = (ConsultantTime) o;

        if (hours != that.hours) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (skill != that.skill) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (skill != null ? skill.hashCode() : 0);
        result = 31 * result + hours;
        return result;
    }

    /**
     * Creates a string representation of the consultant time.
     *
     * @return this ConsultantTime as a formatted string.
     */
    @Override
    public String toString() {
        return String.format("%-26s  %2$tm/%2$td/%2$tY  %3$5d  %4$s%n",
                account.getName(), date, hours, skill.getName());
    }
}
