package com.scg.domain;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 2/2/13
 * Time: 9:32 AM
 */
public class SickLeaveHoursChangeEvent extends EventObject {
    static final long serialVersionUID = -5203434926922335766L;

    protected int hours;

    public SickLeaveHoursChangeEvent(Object source, int hours) {
        super(source);
        this.hours = hours;
    }
}
