package com.scg.domain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventListener;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 2/2/13
 * Time: 1:17 PM
 */
public class BenefitManager implements PropertyChangeListener, EventListener {

    private static final Logger LOGGER = Logger.getLogger("BenefitManager.class");
    /**
     * Logs the change
     * @param evt - a property change event for the sickLeaveHours or vacationHours property
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propName = evt.getPropertyName();
        int oldValue = (Integer) evt.getOldValue();
        int newValue = (Integer) evt.getNewValue();
        Consultant consultant = (Consultant) evt.getSource();
        LOGGER.info(
                String.format("%s changed from %d to %d for %s",
                        propName, oldValue, newValue, consultant.getName()));
    }
}
