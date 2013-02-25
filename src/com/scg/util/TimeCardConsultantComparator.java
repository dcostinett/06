package com.scg.util;

import com.scg.domain.TimeCard;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/27/13
 * Time: 7:50 AM
 */
@SuppressWarnings({"serial", "unchecked"})
public class TimeCardConsultantComparator implements Comparator<TimeCard>, Serializable {
    @Override
    /**
     * Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first
     * argument is less than, equal to, or greater than the second.
     * @param lhs - the first time card
     * @param rhs - the other time card
     *
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or
     * greater than the second.
     */
    public int compare(TimeCard lhs, TimeCard rhs) {
        return lhs.getConsultant().getName().compareTo(rhs.getConsultant().getName());
    }
}
