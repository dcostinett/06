package com.scg.domain;

import com.scg.util.Name;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 2/2/13
 * Time: 1:14 PM
 */
public class CompensationManager implements PropertyChangeListener, VetoableChangeListener {

    private static final Logger LOGGER = Logger.getLogger("CompensationManager.class");
    /**
     * Processes to final pay rate change.
     * @param evt - a change event for the payRate property
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int oldValue = (Integer) evt.getOldValue();
        int newValue = (Integer) evt.getNewValue();
        Consultant consultant = (Consultant) evt.getSource();
        LOGGER.info(String.format("Pay rate changed, from %d to %d for %s",
                oldValue, newValue, consultant.getName()));
    }

    /**
     * Rejects any raise over 5%.
     * @param evt - a vetoable change event for the payRate property
     * @throws PropertyVetoException
     */
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        long oldValue = (Integer) evt.getOldValue();
        long newValue = (Integer) evt.getNewValue();
        long rateChange = (newValue - oldValue) / oldValue;

        Consultant consultant = (Consultant) evt.getSource();
        if (newValue > (oldValue * 1.05)) {
            LOGGER.info(String.format("REJECTED pay rate change, from %d to %d for %s", oldValue, newValue, consultant.getName()));
            throw new PropertyVetoException("Unable to increase pay more than 5% at a time", evt);
        }
        LOGGER.info(String.format("APPROVED pay rate change, from %d to %d for %s", oldValue, newValue, consultant.getName()));
    }
}
