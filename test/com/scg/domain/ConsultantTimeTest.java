package com.scg.domain;

import com.scg.util.Name;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/20/13
 * Time: 7:19 AM
 */
public class ConsultantTimeTest {

    private static final Calendar cal = new GregorianCalendar();
    private static final Date now = cal.getTime();
    private static final ClientAccount account = new ClientAccount("MyTestClient", new Name("Last", "First"));
    private static final ConsultantTime consultantTime = new ConsultantTime(now, account, Skill.PROJECT_MANAGER, 100);


    @Test
    public void testGetDate() throws Exception {

        Date when = consultantTime.getDate();
        assertTrue(now.equals(when));
    }

    @Test
    public void testSetDate() throws Exception {
        Date when = consultantTime.getDate();
        assertTrue(now.equals(when));

        //add a month YEAR == 1, MONTH == 2
        cal.add(Calendar.MONTH, 1);

        consultantTime.setDate(cal.getTime());
        when = consultantTime.getDate();
        assertTrue(cal.getTime().equals(when));
    }

    @Test
    public void testGetAccount() throws Exception {
        assertEquals(account, consultantTime.getAccount());
    }

    @Test
    public void testSetAccount() throws Exception {
        assertEquals(account, consultantTime.getAccount());

        ClientAccount otherClient = new ClientAccount("MyOtherTestClient", new Name("OtherLast", "OtherFirst"));
        consultantTime.setAccount(otherClient);
    }

    @Test
    public void testGetSkill() throws Exception {
        assertEquals("Expected Kill = Skill.PROJECT_MANAGER", Skill.PROJECT_MANAGER, consultantTime.getSkill());
        Assert.assertEquals(Skill.PROJECT_MANAGER, consultanttime.getSkill());
    }

    @Test
    public void testGetHours() throws Exception {
        //test object's constructor initializes value to 100
        assertEquals("Expected hours to initially be 100", 100, consultantTime.getHours());
    }

    @Test
    public void testSetHours() throws Exception {
        consultantTime.setHours(10);
        assertEquals("Expected hours to be 10", 10, consultantTime.getHours());
        consultantTime.setHours(100);
        assertEquals("Expected hours to be 100", 100, consultantTime.getHours());
    }

    /** Constant for test year. */
    private static final int TEST_YEAR = 2004;
    /** Constant for start day. */
    private static final int START_DAY = 5;
    /** Constant for hours per day. */
    private static final int HOURS_PER_DAY = 8;
    /** String constant for "FooBar, Inc.". */
    private static final String FOOBAR_INC = "FooBar, Inc.";
    /** String constant for "Mouse". */
    private static final String MOUSE = "Mouse";
    /** String constant for "Mickey". */
    private static final String MICKEY = "Mickey";

    /** Error message if an IllegalArgumentException isn't caught. */
    private static final String FAILED_ILLEGAL_ARG_EX_MSG = "Failed to throw IllegalArgumentException.";
    /** ConsultantTime instance for test. */
    private ConsultantTime consultanttime;

    /** Calendar instance for test. */
    private final Calendar calendar = new GregorianCalendar(TEST_YEAR, Calendar.JANUARY, START_DAY);

    /** Date instance for test. */
    private Date date;

    /** ClientAccount instance for test. */
    private final ClientAccount client = new ClientAccount(FOOBAR_INC, new Name(MOUSE, MICKEY));

    /**
     * Perform test setup.
     */
    @Before
    public void setUp() {
        date = calendar.getTime();
        consultanttime = new ConsultantTime(date, client, Skill.PROJECT_MANAGER, HOURS_PER_DAY);
    }

    /**
     * Perform test tear down.
     */
    @After
    public void tearDown() {
        consultanttime = null;
    }

    /**
     * Test constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor() {
        consultanttime = new ConsultantTime(date,
                NonBillableAccount.SICK_LEAVE, Skill.UNKNOWN_SKILL, -HOURS_PER_DAY);
    }

    /**
     * Tests the getDate and setDate methods.
     */
    @Test
    public void testSetGetDate() {
        final Date[] tests = {new Date(), new Date(0), null};

        for (int i = 0; i < tests.length; i++) {
            consultanttime.setDate(tests[i]);
            Assert.assertEquals(tests[i], consultanttime.getDate());
        }
    }

    /**
     * Tests the getAccount and setAccount methods.
     */
    @Test
    public void testSetGetAccount() {
        final Account[] tests = {client, null};

        for (int i = 0; i < tests.length; i++) {
            consultanttime.setAccount(tests[i]);
            Assert.assertEquals(tests[i], consultanttime.getAccount());
        }
    }

    /**
     * Tests the isBillable method.
     */
    @Test
    public void testIsBillable() {
        assertTrue(consultantTime.isBillable());
        assertEquals(account.isBillable(), consultantTime.isBillable());

        consultanttime = new ConsultantTime(date, client,
                Skill.PROJECT_MANAGER, HOURS_PER_DAY);
        Assert.assertTrue(consultanttime != null);
        Assert.assertTrue(consultanttime.isBillable());
        // Test a non-billable account
        consultanttime = new ConsultantTime(date, NonBillableAccount.SICK_LEAVE,
                Skill.UNKNOWN_SKILL, HOURS_PER_DAY);
        Assert.assertTrue(consultanttime != null);
        assertFalse(consultanttime.isBillable());
    }

    /**
     * Tests the getHours and setHours methods.
     */
    @Test
    public void testSetGetHours() {
        int[] tests = new int[] {Integer.MIN_VALUE, 0, -HOURS_PER_DAY};
        try {
            // Test the assertion that hours must be positive.
            consultanttime.setHours(-HOURS_PER_DAY);
            fail(FAILED_ILLEGAL_ARG_EX_MSG);
        } catch (final IllegalArgumentException ex) {
            System.out.println("Caught expected exception (testSetGetHours).");
            // ex.printStackTrace();
        }

        tests = new int[] {1, Integer.MAX_VALUE};
        for (int i = 0; i < tests.length; i++) {
            consultanttime.setHours(tests[i]);
            Assert.assertEquals(tests[i], consultanttime.getHours());

        }
    }


    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        consultanttime = new ConsultantTime(date,
                new ClientAccount(FOOBAR_INC, new Name(MOUSE, MICKEY)),
                Skill.SOFTWARE_ENGINEER, HOURS_PER_DAY);
        Assert.assertTrue(consultanttime.toString() != null && !consultanttime.toString().isEmpty());
    }
}
