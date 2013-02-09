package com.scg.domain;

import com.scg.util.Name;

import javax.swing.event.EventListenerList;
import java.beans.*;
import java.util.EventListenerProxy;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/31/13
 * Time: 8:00 AM
 */
public class StaffConsultant extends Consultant {
    static final long serialVersionUID = -5366268816864513537L;

    public static final String PAY_RATE_PROPERTY_NAME          = "payRate";
    public static final String SICK_LEAVE_HOURS_PROPERTY_NAME  = "sickLeaveHours";
    public static final String VACATION_HOURS_PROPERTY_NAME    = "vacationHours";

    int payRate;
    int sickLeaveHours;
    int vacationHours;

    private final VetoableChangeSupport vetoSupport = new VetoableChangeSupport(this);
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final Logger LOGGER = Logger.getLogger("StaffConsultant.class");

    public StaffConsultant(Name name,
                           int rate,
                           int sickLeave,
                           int vacation) {
        super(name);
        this.payRate = rate;
        this.sickLeaveHours = sickLeave;
        this.vacationHours = vacation;
    }


    public int getPayRate() {
        return payRate;
    }

    public void setPayRate(int payRate) throws PropertyVetoException {
        int oldValue = getPayRate();
        vetoSupport.fireVetoableChange(PAY_RATE_PROPERTY_NAME, oldValue, payRate);
        this.payRate = payRate;
        changeSupport.firePropertyChange(PAY_RATE_PROPERTY_NAME, oldValue, payRate);
    }

    public int getSickLeaveHours() {
        return sickLeaveHours;
    }

    public void setSickLeaveHours(int sickLeaveHours) {
        int oldHours = getSickLeaveHours();
        this.sickLeaveHours = sickLeaveHours;
        changeSupport.firePropertyChange(SICK_LEAVE_HOURS_PROPERTY_NAME, oldHours, sickLeaveHours);
    }

    public int getVacationHours() {
        return vacationHours;
    }

    public void setVacationHours(int vacationHours) throws PropertyVetoException {
        int oldHours = getVacationHours();
        //vetoSupport.fireVetoableChange("vacationHours", oldHours, vacationHours);
        this.vacationHours = vacationHours;
        changeSupport.firePropertyChange(VACATION_HOURS_PROPERTY_NAME, oldHours, vacationHours);
    }

    /**
     * Adds a general property change listener.
     * @param l - the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changeSupport.addPropertyChangeListener(l);
    }

    /**
     * Remove a general property change listener.
     * @param l - the listener
     */
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changeSupport.removePropertyChangeListener(l);
    }

    /**
     * Adds a payRate property change listener.
     * @param l - the listener
     */
    public void addPayRateListener(PropertyChangeListener l) {
        changeSupport.addPropertyChangeListener(PAY_RATE_PROPERTY_NAME, l);
    }

    /**
     * Removes a payRate property change listener.
     * @param l - the listener
     */
    public void removePayRateListener(PropertyChangeListener l) {
        changeSupport.removePropertyChangeListener(l);
    }

    /**
     * Adds a vetoable change listener.
     * @param l - the listener
     */
    public void addVetoableChangeListener(VetoableChangeListener l) {
        vetoSupport.addVetoableChangeListener(l);
    }

    /**
     * Removes a vetoable change listener.
     * @param l - the listener
     */
    public void removeVetoableChangeListener(VetoableChangeListener l) {
        vetoSupport.removeVetoableChangeListener(l);
    }

    public synchronized void addSickLeaveHoursListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(SICK_LEAVE_HOURS_PROPERTY_NAME, listener);
    }

    public synchronized void addVacationHoursListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(VACATION_HOURS_PROPERTY_NAME, listener);
    }

    public synchronized void removeSickLeaveHoursListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public synchronized void removeVacationHoursListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
