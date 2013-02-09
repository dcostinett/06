package com.scg.domain;

import java.util.EventListener;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 2/2/13
 * Time: 9:26 AM
 */
public interface VacationHoursChangeListener extends EventListener {
    void vacationHoursChanged(VacationHoursChangedEvent event);
}
