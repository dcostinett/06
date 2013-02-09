package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.DateRange;
import com.scg.util.StateCode;
import com.scg.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/21/13
 * Time: 2:25 PM
 *
 * Invoice encapsulates the attributes and behavior to create client invoices for a given time period from time cards.
 * The invoicing business' name and address are obtained from a properties file. The name of the property file is
 * specified by the PROP_FILE_NAME static member.
 */
public final class Invoice {
    /**
     * Name of property file containing invoicing business info.
     */
    public static final String PROP_FILE_NAME = "invoice.properties";

    /**
     * Property containing the invoicing business name.
     */
    public static final String BUSINESS_NAME_PROP = "business.name";

    /**
     * Property containing the invoicing business street address.
     */
    public static final String BUSINESS_STREET_PROP = "business.street";


    /**
     * Property containing the invoicing business city.
     */
    public static final String BUSINESS_CITY_PROP = "business.city";

    /**
     * Property containing the invoicing business state.
     */
    public static final String BUSINESS_STATE_PROP = "business.state";

    /**
     * Property containing the invoicing business zip or postal code.
     */
    public static final String BUSINESS_ZIP_PROP = "business.zip";

    /**
     * String constant for "N/A"
     */
    public static final String NA = "N/A";

    private final ClientAccount client;
    private final int invoiceMonth;
    private final int invoiceYear;

    private final Date startDate;
    private final Date endDate;

    private final DateRange dateRange;

    private final int maxItemsPerPage = 5;

    private List<InvoiceLineItem> lineItems = new ArrayList<InvoiceLineItem>();
    private final Properties invoiceProperties = new Properties();

    /**
     * Construct an Invoice for a client. The time period is set from the beginning to the end of the month specified.
     * @param client - Client for this Invoice.
     * @param invoiceMonth - Month for which this Invoice is being created. This parameter is the 0-based month number.
     *                     The safest way for callers to pass a valid value is to use Calendar.MONTHNAME for the
     *                     constant.
     * @param invoiceYear - Year for which this Invoice is being created.
     */
    public Invoice(ClientAccount client, int invoiceMonth, int invoiceYear) {
        this.client = client;
        this.invoiceMonth = invoiceMonth;
        this.invoiceYear = invoiceYear;

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.MONTH, invoiceMonth);
        cal.set(Calendar.YEAR, invoiceYear);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
        startDate = cal.getTime();

        cal.set(Calendar.DATE, cal.getMaximum(Calendar.DAY_OF_MONTH));
        endDate = cal.getTime();

        this.dateRange = new DateRange(startDate, endDate);

        try {
            invoiceProperties.load(this.getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Invoice(ClientAccount client, DateRange dateRange) {
        this.client = client;
        this.dateRange = dateRange;
        this.startDate = dateRange.getStartDate();
        this.endDate = dateRange.getEndDate();
        Calendar cal = new GregorianCalendar();
        cal.setTime(startDate);
        this.invoiceMonth = cal.get(Calendar.MONTH);
        this.invoiceYear = cal.get(Calendar.YEAR);
    }

    /**
     * Get the start date for this Invoice.
     * @return - Start date for this invoice.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Get the end date for this Invoice.
     * @return - End date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Get the invoice month. This is the 0-based month number.
     * @return - 0-based invoice month.
     */
    public int getInvoiceMonth() {
        return invoiceMonth;
    }

    /**
     * Get the total hours for this Invoice.
     *
     * @return - Total hours
     */
    public int getTotalHours() {
        int hours = 0;
        for (InvoiceLineItem lineItem : lineItems) {
            hours += lineItem.getHours();
        }
        return hours;
    }

    /**
     * Get the total charges for this Invoice.
     *
     * @return - Total charges
     */
    public int getTotalCharges() {
        int charges = 0;
        for (InvoiceLineItem lineItem : lineItems) {
            charges += lineItem.getCharge();
        }
        return charges;
    }

    /**
     * Extract the billable hours for this Invoice's client from the input TimeCard and add them to the line items.
     * Only those hours for the client and month unique to this invoice will be added.
     * @param timeCard - the TimeCard potentially containing line items for this Invoices client.
     */
    public void extractLineItems(TimeCard timeCard) {
        List<ConsultantTime> consultantTimes = timeCard.getBillableHoursForClient(client.getName());
        for (ConsultantTime time : consultantTimes) {
            InvoiceLineItem lineItem = new InvoiceLineItem(
                    time.getDate(),
                    timeCard.getConsultant(),
                    time.getSkill(),
                    time.getHours());
            Calendar cal = new GregorianCalendar();
            cal.setTime(lineItem.getDate());
            if (dateRange.isInRange(cal.getTime())) {
                lineItems.add(lineItem);
            }
        }
    }



    private String getPageHeader(InvoiceHeader header) {
        StringBuilder sb = new StringBuilder();
        sb.append(header.toString()).append("\n");

        sb.append("Invoice for: ").append("\n");
        sb.append(client.getName()).append("\n");
        sb.append(client.getAddress()).append("\n");
        sb.append(client.getContact()).append("\n");

        sb.append("\n");
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.MONTH, invoiceMonth);
        cal.set(Calendar.YEAR, invoiceYear);
        sb.append(String.format("Invoice for month of: %1$tB %1$tY", cal)).append("\n");

        cal.setTime(new Date(System.currentTimeMillis()));
        sb.append(String.format("Invoice Date: %1$tB %1$td, %1$tY", cal)).append("\n");
        sb.append("\n");

        sb.append(String.format("%-10s  %-29s  %-18s  %-5s  %-10s", "Date", "Consultant", "Skill", "Hours", "Charge")).append("\n");
        sb.append(StringUtils.pad('-', 10)).append("  ")
                .append(StringUtils.pad('-', 29)).append("  ")
                .append(StringUtils.pad('-', 18)).append("  ")
                .append(StringUtils.pad('-', 5)).append("  ")
                .append(StringUtils.pad('-', 10))
                .append("\n");
        return sb.toString();
    }


    @Override
    /**
     * Create a formatted string containing the printable invoice. Prints a header and footer on each page.
     *
     * @return - The formatted invoice as a string.
     */
    public String toString() {

        Address addr = new Address(invoiceProperties.getProperty(BUSINESS_STREET_PROP),
                invoiceProperties.getProperty(BUSINESS_CITY_PROP),
                StateCode.valueOf(invoiceProperties.getProperty(BUSINESS_STATE_PROP)),
                invoiceProperties.getProperty(BUSINESS_ZIP_PROP));

        InvoiceHeader invoiceHeader = new InvoiceHeader(
                invoiceProperties.getProperty(BUSINESS_NAME_PROP),
                addr,
                startDate,
                new Date(invoiceMonth));

        InvoiceFooter footer = new InvoiceFooter(invoiceProperties.getProperty(BUSINESS_NAME_PROP));

        StringBuilder sb = new StringBuilder();
        sb.append(getPageHeader(invoiceHeader));

        int itemCount = 0;
        for (InvoiceLineItem lineItem : lineItems) {
            sb.append(lineItem.toString()).append("\n");
            if (++itemCount >= maxItemsPerPage) {
                sb.append("\n");
                sb.append(footer);
                footer.incrementPageNumber();
                sb.append(getPageHeader(invoiceHeader));
                itemCount = 0;
            }
        }

        sb.append("\n");
        sb.append(String.format("Total: %61d  %,10.2f", getTotalHours(), new Float(getTotalCharges()))).append("\n");

        sb.append(footer);

        return sb.toString();
    }

}
