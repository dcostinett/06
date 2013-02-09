/**
 * Created with IntelliJ IDEA.
 * Author: dcostinett
 * Date: 1/12/13
 * Time: 3:51 PM
 *
 * Base interface that all accounts must implement
 */

package com.scg.domain;

public interface Account {

    /**
     * Getter for the name of this account.
     * @return the name of this account.
     */
    String getName();


    /**
     * Determines if this account is billable.
     * @return true if the account is billable, otherwise false.
     */
    boolean isBillable();
}
