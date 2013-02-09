package com.scg.domain;

import com.scg.util.Name;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/21/13
 * Time: 7:55 AM
 */
public class TimeCardTest {
    public static final Consultant CONSULTANT = new Consultant(new Name("Last", "First"));
    public static final Calendar CALENDAR = new GregorianCalendar(2006, 2, 27);
    public static final TimeCard TIME_CARD = new TimeCard(CONSULTANT, CALENDAR.getTime());

    /** Constant for test year. */
    private static final int TEST_YEAR = 2004;
    /** Constant for hours per day. */
    private static final int HOURS_PER_DAY = 8;
    /** Constant for start day. */
    private static final int START_DAY = 6;
    /** Constant for expected total hours. */
    private static final int EXPECTED_TOTAL_HOURS = 16;
    /** Constant for expected total billable hours. */
    private static final int EXPECTED_TOTAL_BILLABLE_HOURS = 16;
    /** Constant for expected total non-billable hours. */
    private static final int EXPECTED_TOTAL_NON_BILLABLE_HOURS = 16;
    /** Constant for expected consulting hours. */
    private static final int EXPECTED_CONSULTING_HOURS = 3;
    /** Constant for expected billable consulting hours. */
    private static final int EXPECTED_BILLABLE_CONSULTING_HOURS = 24;
    /** Constant for expected non-billable consulting hours. */
    private static final int EXPECTED_NON_BILLABLE_CONSULTING_HOURS = 32;
    /** TimeCard for test. */
    private TimeCard timecard;
    /** ClientAccount for test. */
    private ClientAccount client;
    /** Calendar for test. */
    private final Calendar calendar = new GregorianCalendar(TEST_YEAR, Calendar.JANUARY, START_DAY);
    /** Date for test. */
    private Date date;
    /** Date representing next day for test. */
    private Date nextDay;
    /** Consultant for test. */
    private Consultant programmer;

    /**
     * Perform test setup.
     */
    @Before
    public void setUp() {
        client = new ClientAccount("Acme Industries",
                new Name("Contact", "Guy"));
        programmer = new Consultant(new Name("Programmer", "J.", "Random"));
        date = calendar.getTime();
        timecard = new TimeCard(programmer, date);
        final NonBillableAccount nonbillableaccount = NonBillableAccount.VACATION;
        ConsultantTime consultantTime = new ConsultantTime(date, client,
                Skill.SYSTEM_ARCHITECT, HOURS_PER_DAY);
        timecard.addConsultantTime(consultantTime);
        calendar.roll(Calendar.DAY_OF_MONTH, 1);
        nextDay = calendar.getTime();
        consultantTime = new ConsultantTime(date, nonbillableaccount,
                Skill.SYSTEM_ARCHITECT, HOURS_PER_DAY);
        timecard.addConsultantTime(consultantTime);

    }

    /**
     * Perform test tear down.
     */
    @After
    public void tearDown() {
        timecard = null;
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddConsultantTime() throws Exception {
        ConsultantTime consultantTime = new ConsultantTime(date,
                client, Skill.SYSTEM_ARCHITECT, HOURS_PER_DAY);
        //Date, Account, Skill, hours
        Calendar cal = new GregorianCalendar();
        Name name = new Name("Z", "A");
        Account clientAccount = new ClientAccount("MyTestAccount", name);

        //next line should throw IllegalArgumentException due to hours = 0
        consultantTime = new ConsultantTime(cal.getTime(), clientAccount, Skill.PROJECT_MANAGER, 0);

        fail();
    }

    @Test
    public void testGetBillableHoursForClient() throws Exception {
        Calendar cal = new GregorianCalendar();
        Name name = new Name("Z", "A");
        Account clientAccount = new ClientAccount("MyTestAccount", name);
        ConsultantTime consultantTime = new ConsultantTime(cal.getTime(), clientAccount, Skill.PROJECT_MANAGER, 5);

        Consultant consultant = new Consultant(name);
        TimeCard timeCard = new TimeCard(consultant, cal.getTime());
        timeCard.addConsultantTime(consultantTime);

        assertEquals(0, timeCard.getTotalNonBillableHours());

        List<ConsultantTime> hours = timeCard.getBillableHoursForClient(clientAccount.getName());
        assertEquals(1, hours.size());
        assertEquals(5, hours.get(0).getHours());

        hours = timeCard.getBillableHoursForClient("Someone Else");
        assertNotNull(hours);
        assertTrue(hours.isEmpty());
    }

    @Test
    public void testGetConsultant() throws Exception {
        Calendar cal = new GregorianCalendar();
        Name name = new Name("Z", "A");
        Account clientAccount = new ClientAccount("MyTestAccount", name);
        ConsultantTime consultantTime = new ConsultantTime(cal.getTime(), clientAccount, Skill.PROJECT_MANAGER, 5);

        Consultant consultant = new Consultant(name);
        TimeCard timeCard = new TimeCard(consultant, cal.getTime());

        assertEquals(consultant, timeCard.getConsultant());
    }

    @Test
    public void testGetWeekStartingDay() throws Exception {
        Calendar cal = new GregorianCalendar();
        Name name = new Name("Z", "A");
        Account clientAccount = new ClientAccount("MyTestAccount", name);
        ConsultantTime consultantTime = new ConsultantTime(cal.getTime(), clientAccount, Skill.PROJECT_MANAGER, 5);

        Consultant consultant = new Consultant(name);
        TimeCard timeCard = new TimeCard(consultant, cal.getTime());

        assertEquals(cal.getTime(), timeCard.getWeekStartingDay());
    }

    @Test
    public void testGetConsultingHours() throws Exception {
        Calendar cal = new GregorianCalendar();
        Name name = new Name("Z", "A");
        Account clientAccount = new ClientAccount("MyTestAccount", name);
        ConsultantTime consultantTime = new ConsultantTime(cal.getTime(), clientAccount, Skill.PROJECT_MANAGER, 5);

        Consultant consultant = new Consultant(name);
        TimeCard timeCard = new TimeCard(consultant, cal.getTime());

        List<ConsultantTime> hours = timeCard.getConsultingHours();
        assertNotNull(hours);
        assertTrue(hours.isEmpty());

        timeCard.addConsultantTime(consultantTime);
        hours = timeCard.getConsultingHours();
        assertTrue(!hours.isEmpty());
        assertEquals(1, hours.size());
        ConsultantTime time = hours.get(0);
        assertEquals(consultantTime, time);
    }

    @Test
    public void testGetBillableHours() throws Exception {
        Calendar cal = new GregorianCalendar();
        Name name = new Name("Z", "A");
        Account clientAccount = new ClientAccount("MyTestAccount", name);
        ConsultantTime consultantTime = new ConsultantTime(cal.getTime(), clientAccount, Skill.PROJECT_MANAGER, 5);

        Consultant consultant = new Consultant(name);
        TimeCard timeCard = new TimeCard(consultant, cal.getTime());

        List<ConsultantTime> hours = timeCard.getConsultingHours();
        assertNotNull(hours);
        assertTrue(hours.isEmpty());

        timeCard.addConsultantTime(consultantTime);
        assertEquals(5, timeCard.getBillableHours());
    }

    @Test
    public void testGetTotalHours() throws Exception {
        Calendar cal = new GregorianCalendar();
        Name name = new Name("Z", "A");
        Account clientAccount = new ClientAccount("MyTestAccount", name);
        ConsultantTime consultantTime = new ConsultantTime(cal.getTime(), clientAccount, Skill.PROJECT_MANAGER, 5);

        Consultant consultant = new Consultant(name);
        TimeCard timeCard = new TimeCard(consultant, cal.getTime());

        List<ConsultantTime> hours = timeCard.getConsultingHours();
        assertNotNull(hours);
        assertTrue(hours.isEmpty());

        timeCard.addConsultantTime(consultantTime);
        assertEquals(5, timeCard.getBillableHours());
        assertEquals(5, timeCard.getTotalHours());

        timeCard.addConsultantTime(new ConsultantTime(cal.getTime(), clientAccount, Skill.UNKNOWN_SKILL, 100));
        assertEquals(105, timeCard.getBillableHours());
    }

    @Test
    public void testGetTotalNonBillableHours() throws Exception {
        Calendar cal = new GregorianCalendar();
        Name name = new Name("Z", "A");
        Account clientAccount = new ClientAccount("MyTestAccount", name);
        ConsultantTime consultantTime = new ConsultantTime(cal.getTime(), clientAccount, Skill.PROJECT_MANAGER, 5);

        Consultant consultant = new Consultant(name);
        TimeCard timeCard = new TimeCard(consultant, cal.getTime());

        List<ConsultantTime> hours = timeCard.getConsultingHours();
        assertNotNull(hours);
        assertTrue(hours.isEmpty());

        timeCard.addConsultantTime(consultantTime);
        assertEquals(5, timeCard.getBillableHours());
        assertEquals(0, timeCard.getTotalNonBillableHours());

        timeCard.addConsultantTime(new ConsultantTime(
                cal.getTime(), NonBillableAccount.VACATION, Skill.SYSTEM_ARCHITECT, 1000));
        assertEquals(1000, timeCard.getTotalNonBillableHours());
    }
}
