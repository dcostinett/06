package com.scg.domain;

import java.util.EventListener;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 2/2/13
 * Time: 9:26 AM
 */
public interface SickLeaveHoursChangeListener extends EventListener {
    void sickLeaveHoursChanged(SickLeaveHoursChangeEvent event);
}
