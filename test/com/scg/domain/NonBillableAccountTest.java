package com.scg.domain;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/21/13
 * Time: 7:45 AM
 */
public class NonBillableAccountTest {
    private static final NonBillableAccount NONBILLABLE_BUS_DEV_TestAccount = NonBillableAccount.BUSINESS_DEVELOPMENT;
    private static final NonBillableAccount NONBILLABLE_SICK_LEAVE_TestAccount = NonBillableAccount.SICK_LEAVE;
    private static final NonBillableAccount NONBILLABLE_VACATION_TestAccount = NonBillableAccount.VACATION;

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Business Development", NONBILLABLE_BUS_DEV_TestAccount.getName());
        Assert.assertEquals("Sick Leave", NONBILLABLE_SICK_LEAVE_TestAccount.getName());
        Assert.assertEquals("Vacation", NONBILLABLE_VACATION_TestAccount.getName());
    }

    @Test
    public void testIsBillable() throws Exception {
        assertTrue(!NONBILLABLE_BUS_DEV_TestAccount.isBillable());
        assertTrue(!NONBILLABLE_VACATION_TestAccount.isBillable());
        assertTrue(!NONBILLABLE_SICK_LEAVE_TestAccount.isBillable());
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals("Business Development", NONBILLABLE_BUS_DEV_TestAccount.toString());
        Assert.assertEquals("Business Development", NONBILLABLE_BUS_DEV_TestAccount.toString());
        Assert.assertEquals("Vacation", NONBILLABLE_VACATION_TestAccount.toString());
    }
}
