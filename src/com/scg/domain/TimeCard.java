package com.scg.domain;

import com.scg.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/19/13
 * Time: 11:53 AM
 * Encapsulates a time card capable of storing a consultant's billable and non-billable hours for a week.
 */
public class TimeCard implements Comparable<TimeCard>, Serializable {
    private static final long serialVersionUID  = -5458871713592119718L;

    /** Format string for the time card header. */
    private static final String HEADER_FORMAT = "Consultant: %-29s Week Starting: %2$tb %2$td,%2$tY\n";

    /** Format string for a line header on the time card. */
    private static final String LINE_HEADER_FORMAT = String.format("%-28s %-10s  %5s  %s%n"
            + "---------------------------  ----------  -----  --------------------%n",
            "Account", "Date", "Hours", "Skill");

    /** A border for the time card */
    private static final String CARD_BORDER = "====================================================================%n";

    /** Format string for a line on the time card. */
    private static final String LINE_FORMAT = "%-28s %2$tm/%2$td/%2$tY  %3$5d  %4$s%n";

    /** Format string for a summary line on the time card. */
    private static final String SUMMARY_LINE_FORMAT = "%-39s  %5d%n";

    /** Format string for the billable time section header on the time card. */
    private static final String BILLABLE_TIME_HEADER_FORMAT = "%nBillable Time:%n";

    /** Format string for the non-billable time section header on the time card. */
    private static final String NON_BILLABLE_TIME_HEADER_FORMAT = "%nNon-billable Time:%n";

    /** Format string for the summary section header on the time card. */
    private static final String SUMMARY_HEADER_FORMAT = "%nSummary:%n";



    private final Consultant consultant;
    private final Date weekStartingDay;
    private final List<ConsultantTime> consultingHours;
    private final List<ConsultantTime> nonBillableHours;
    private int billableHours = 0;
    private int totalNonBillableHours = 0;

    public TimeCard(final Consultant consultant, final Date weekStartingDay) {
        this.consultant = consultant;
        this.weekStartingDay = new Date(weekStartingDay.getTime());
        consultingHours = new ArrayList<ConsultantTime>();
        nonBillableHours = new ArrayList<ConsultantTime>();
    }

    /**
     * Add a consultantTime object to this Time Card
     *
     * @param consultantTime - consultant time
     */
    public void addConsultantTime(ConsultantTime consultantTime) {
        if (consultantTime.isBillable()) {
            consultingHours.add(consultantTime);
            billableHours += consultantTime.getHours();
        } else {
            nonBillableHours.add(consultantTime);
            totalNonBillableHours += consultantTime.getHours();
        }
    }

    /**
     * Returns the billable hours (if any) in this TimeCard for the specified client.
     *
     * @param clientName - client name
     * @return List<ConsultantTime> of hours for given client name
     */
    public List<ConsultantTime> getBillableHoursForClient(String clientName) {
        final List<ConsultantTime> clientHours = new ArrayList<ConsultantTime>();

        for (final ConsultantTime consultantTime : consultingHours) {
            if (clientName.equals(consultantTime.getAccount().getName())) {
                if (consultantTime.isBillable()) {
                    clientHours.add(consultantTime);
                }
            }
        }

        return clientHours;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public Date getWeekStartingDay() {
        return weekStartingDay;
    }

    public List<ConsultantTime> getConsultingHours() {
        return consultingHours;
    }

    public int getBillableHours() {
        return billableHours;
    }

    public int getTotalHours() {
        return billableHours + totalNonBillableHours;
    }

    public int getTotalNonBillableHours() {
        return totalNonBillableHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeCard)) return false;

        TimeCard timeCard = (TimeCard) o;

        if (billableHours != timeCard.billableHours) return false;
        if (totalNonBillableHours != timeCard.totalNonBillableHours) return false;
        if (consultant != null ? !consultant.equals(timeCard.consultant) : timeCard.consultant != null) return false;
        if (consultingHours != null ? !consultingHours.equals(timeCard.consultingHours) : timeCard.consultingHours != null)
            return false;
        if (nonBillableHours != null ? !nonBillableHours.equals(timeCard.nonBillableHours) : timeCard.nonBillableHours != null)
            return false;
        if (weekStartingDay != null ? !weekStartingDay.equals(timeCard.weekStartingDay) : timeCard.weekStartingDay != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = consultant != null ? consultant.hashCode() : 0;
        result = 31 * result + (weekStartingDay != null ? weekStartingDay.hashCode() : 0);
        result = 31 * result + (consultingHours != null ? consultingHours.hashCode() : 0);
        result = 31 * result + (nonBillableHours != null ? nonBillableHours.hashCode() : 0);
        result = 31 * result + billableHours;
        result = 31 * result + totalNonBillableHours;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================================================").append("\n");
        Calendar cal = new GregorianCalendar();
        cal.setTime(weekStartingDay);
        sb.append(String.format("Consultant: %-18s %10s Week Starting: %3$tb %3$te,%3$tY",
                consultant.toString(), " ", cal)).append("\n");
        sb.append("\n");
        sb.append("Billable Time:\n");
        sb.append(String.format("%-26s  %-10s  %-5s  %-20s", "Account", "Date", "Hours", "Skill")).append("\n");
        sb.append(StringUtils.pad('-', 26)).append("  ")
                .append(StringUtils.pad('-', 10)).append("  ")
                .append(StringUtils.pad('-', 5)).append("  ")
                .append(StringUtils.pad('-', 20))
                .append("\n");
        for (ConsultantTime hours : consultingHours) {
            if (hours.isBillable()) {
                sb.append(hours.toString());
            }
        }
        if (!nonBillableHours.isEmpty()) {
            sb.append("\n");
            sb.append("Non-billable Time:\n");
            sb.append(String.format("%-26s  %-10s  %-5s  %-20s", "Account", "Date", "Hours", "Skill")).append("\n");
            sb.append(StringUtils.pad('-', 26)).append("  ")
                    .append(StringUtils.pad('-', 10)).append("  ")
                    .append(StringUtils.pad('-', 5)).append("  ")
                    .append(StringUtils.pad('-', 20))
                    .append("\n");
            for (ConsultantTime hours : nonBillableHours) {
                if (!hours.isBillable()) {
                    sb.append(hours.toString());
                }
            }
        }
        sb.append("\n");
        sb.append("Summary: \n");
        sb.append(String.format("Total %s: %29d\n", "Billable", billableHours));
        sb.append(String.format("Total Non-Billable: %25d\n", totalNonBillableHours));
        sb.append(String.format("Total Hours: %32d\n", getTotalHours()));
        sb.append("====================================================================").append("\n");
        return sb.toString();
    }

    @Override
    public int compareTo(TimeCard o) {
        return consultant.compareTo(o.getConsultant());
    }
}
