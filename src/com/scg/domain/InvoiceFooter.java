package com.scg.domain;

import com.scg.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/21/13
 * Time: 2:26 PM
 */
public class InvoiceFooter {
    private final String businessName;
    private volatile int pageNumber = 1;

    public InvoiceFooter(String businessName) {
        this.businessName = businessName;
    }

    public void incrementPageNumber() {
        pageNumber++;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(String.format("%-70s Page:%4d", businessName, pageNumber)).append("\n");
        sb.append(StringUtils.pad('=', 80));
        sb.append("\n");
        return sb.toString();
    }
}
