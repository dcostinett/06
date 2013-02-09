package com.scg.domain;

import java.util.EventListener;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 2/2/13
 * Time: 1:08 PM
 */
public interface TerminationListener extends EventListener {

    /**
     * Invoked when a consultant is voluntarily terminated.
     * @param evt - the termination event
     */
    void voluntaryTermination(TerminationEvent evt);

    /**
     * Invoked when a consultant is involuntarily terminated.
     * @param evt - the termination event
     */
    void forcedTermination(TerminationEvent evt);
}
