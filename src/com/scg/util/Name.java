package com.scg.util;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/12/13
 * Time: 5:23 PM
 * Encapsulates the first, middle and last name of a person.
 */
@SuppressWarnings({"serial", "unchecked"})
public class Name implements Serializable {

    private String firstName;
    private String middleName;
    private String lastName;

    /**
     * Creates an instance of a Name.
     */
    public Name() {
    }

    /**
     * Construct a Name.
     *
     * @param lastName  - surname
     * @param firstName - given name
     */
    public Name(String lastName, String firstName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Construct a name.
     *
     * @param lastName   - surname
     * @param firstName  - given name
     * @param middleName - middle name
     */
    public Name(String lastName, String firstName, String middleName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    /**
     * Getter for property firstName
     *
     * @return firstName - given name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for property firstName.
     *
     * @param firstName - new value for given name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for property middleName
     *
     * @return middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for property middleName
     *
     * @param middleName - niddle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Getter for property lastName
     *
     * @return - surname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for property lastName.
     *
     * @param lastName - new value for the surname
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }

        Name name = (Name) o;

        if (!firstName.equals(name.firstName)) {
            return false;
        }

        if (!lastName.equals(name.lastName)) {
            return false;
        }

        return !(middleName != null ? !middleName.equals(name.middleName) : name.middleName != null);

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lastName).append(", ").append(firstName);
        if (middleName != null) {
            sb.append(" ").append(middleName);
        }

        return sb.toString();
    }
}
