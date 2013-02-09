package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/21/13
 * Time: 2:26 PM
 */
public class InvoiceHeader {
    private String businessName;
    private Address businessAddress;
    private Date invoiceDate;
    private Date invoiceForMonth;

    public InvoiceHeader(String businessName, Address businessAddress, Date invoiceDate, Date invoiceForMonth) {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.invoiceDate = invoiceDate;
        this.invoiceForMonth = invoiceForMonth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s", businessName)).append("\n");
        sb.append(String.format("%s", businessAddress.toString()));
        sb.append("\n");

        return sb.toString();
    }
}
