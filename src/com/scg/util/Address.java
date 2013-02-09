package com.scg.util;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/21/13
 * Time: 2:05 PM
 */
@SuppressWarnings({"serial", "unchecked"})
public class Address implements Serializable {
    private final String streetNumber;
    private final String city;
    private final StateCode state;
    private final String postalCode;

    public Address(String streetNumber, String city, StateCode state, String postalCode) {
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    /**
     * Get the street number for this address.
     * @return - the street number
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Gets the city for this address.
     * @return - the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the state for this address.
     * @return - the state
     */
    public StateCode getState() {
        return state;
    }

    /**
     * Gets the postal code for this address.
     * @return - the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null) return false;
        if (state != address.state) return false;
        return !(streetNumber != null ? !streetNumber.equals(address.streetNumber) : address.streetNumber != null);

    }

    @Override
    public int hashCode() {
        int result = streetNumber != null ? streetNumber.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }

    @Override
    /**
     * Returns this address in the form:
     *
     * street number
     * city, state postal code
     *
     * @return - the formatted address
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(streetNumber).append("\n");
        sb.append(String.format("%s, %s %s", city, state, postalCode));

        return sb.toString();
    }
}
