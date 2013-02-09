package com.scg.domain;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 2/2/13
 * Time: 9:30 AM
 */
public class VacationHoursChangedEvent extends EventObject {
    static final long serialVersionUID = -1746463603429225370L;

    protected int hours;

    public VacationHoursChangedEvent(Object source, int hours) {
        super(source);
        this.hours = hours;
    }
}
