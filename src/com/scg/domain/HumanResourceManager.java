package com.scg.domain;

import javax.swing.event.EventListenerList;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 2/2/13
 * Time: 1:02 PM
 *
 * Responsible for modifying the pay rate and sick leave and vacation hours of staff consultants.
 */
public class HumanResourceManager {

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    List<TerminationListener> terminators = new ArrayList<TerminationListener>();

    private static final Logger LOGGER = Logger.getLogger("HumanResourceManager.class");

    public HumanResourceManager() {
    }

    /**
     * Sets the pay rate for a staff consultant.
     * @param c - the consultant
     * @param newPayRate - the new pay rate for the consultant
     */
    public void adjustPayRate(StaffConsultant c,
                              int newPayRate) {
        int oldPayRate = c.getPayRate();
        double changeRate = ((double)newPayRate - (double)oldPayRate) / (double)oldPayRate;
        LOGGER.info(String.format(
                "%% change = (%d - %d)/%d = %1.16f",
                newPayRate, oldPayRate, oldPayRate, changeRate));
        try {
            c.setPayRate(newPayRate);
            LOGGER.info(String.format("Approved pay adjustment for %s", c.getName()));
        } catch (PropertyVetoException e) {
            LOGGER.info(String.format("Denied pay adjustment for %s", c.getName()));
        }
    }


    /**
     * Sets the sick leave hours for a staff consultant.
     * @param c - the consultant
     * @param newSickLeaveHours - the new sick leave hours for the consultant
     */
    public void adjustSickLeaveHours(StaffConsultant c,
                                     int newSickLeaveHours) {
        c.setSickLeaveHours(newSickLeaveHours);
    }

    /**
     * Sets the vacation hours for a staff consultant.
     * @param c - the consultant
     * @param newVacationHours - the new vacation hours for the consultant
     */
    public void adjustVacationHours(StaffConsultant c,
                                    int newVacationHours) {
        try {
            c.setVacationHours(newVacationHours);
        } catch (PropertyVetoException e) {
            LOGGER.info(String.format("Unable to set vacation leave hours for %s to %d", c.getName(), newVacationHours));
            e.printStackTrace();
        }
    }

    /**
     * Fires a voluntary termination event for the staff consultant.
     * @param c - the consultant resigning
     */
    public void acceptResignation(Consultant c) {
        for (TerminationListener terminator : terminators) {
            terminator.voluntaryTermination(new TerminationEvent(this, c, true));
        }
    }

    /**
     * Fires an involuntary termination event for the staff consultant.
     * @param c - the consultant being terminated
     */
    public void terminate(Consultant c) {
        for (TerminationListener terminator : terminators) {
            terminator.forcedTermination(new TerminationEvent(this, c, false));
        }
    }

    /**
     * Adds a termination listener.
     * @param l - the listener to add
     */
    public void addTerminationListener(TerminationListener l) {
        terminators.add(l);
    }

    /**
     * Removes a termination listener.
     * @param l - the listener to remove
     */
    public void removeTerminationListener(TerminationListener l) {
        terminators.remove(l);
    }
}
