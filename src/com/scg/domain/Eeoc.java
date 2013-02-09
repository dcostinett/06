package com.scg.domain;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 2/2/13
 * Time: 1:09 PM
 * The EEOC monitors SCG's terminations.
 */
public class Eeoc implements TerminationListener {

    int forcedTerminationCount;
    int voluntaryTerminationCount;

    private static final Logger LOGGER = Logger.getLogger("Eeoc.class");
    /**
     * Simply prints a message indicating that the consultant quit.
     * @param evt - the termination event
     */
    @Override
    public void voluntaryTermination(TerminationEvent evt) {
        voluntaryTerminationCount++;
        Consultant consultant = evt.getConsultant();
        LOGGER.info(String.format("%s quit.", consultant.getName()));
    }

    /**
     * Simply prints a message indicating that the consultant was fired.
     * @param evt - the termination event
     */
    @Override
    public void forcedTermination(TerminationEvent evt) {
        forcedTerminationCount++;
        Consultant consultant = evt.getConsultant();
        LOGGER.info(String.format("%s was fired.", consultant.getName()));
    }

    /**
     * Gets the forced termination count.
     * @return
     */
    public int forcedTerminationCount() {
        return forcedTerminationCount;
    }

    /**
     * Gets the voluntary termination count.
     * @return
     */
    public int voluntaryTerminationCount() {
        return voluntaryTerminationCount;
    }
}
