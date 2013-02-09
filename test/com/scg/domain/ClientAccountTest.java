package com.scg.domain;

import com.scg.util.Name;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/19/13
 * Time: 2:31 PM
 */
public class ClientAccountTest {

    private static ClientAccount CLIENT_ACCOUNT_1;
    private static ClientAccount CLIENT_ACCOUNT_2;

    private static final Name CLIENT_NAME_FIRST_LAST = new Name("Last", "First");
    private static final Name CLIENT_NAME_FIRST_MIDDLE_LAST = new Name("Last", "First", "Middle");

    @Before
    public void setUp() throws Exception {
        CLIENT_ACCOUNT_1 = new ClientAccount("MyFirstClient", CLIENT_NAME_FIRST_LAST);
        CLIENT_ACCOUNT_2 = new ClientAccount("MySecondClient", CLIENT_NAME_FIRST_MIDDLE_LAST);
    }

    @After
    public void tearDown() throws Exception {
        CLIENT_ACCOUNT_1 = null;
        CLIENT_ACCOUNT_2 = null;
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(CLIENT_ACCOUNT_1.getName(), "MyFirstClient");
        assertEquals(CLIENT_ACCOUNT_2.getName(), "MySecondClient");
    }

    @Test
    public void testIsBillable() throws Exception {
        assertTrue(CLIENT_ACCOUNT_1.isBillable());
        assertTrue(CLIENT_ACCOUNT_2.isBillable());
    }

    @Test
    public void testGetContact() throws Exception {
        assertEquals(CLIENT_ACCOUNT_1.getContact(), CLIENT_NAME_FIRST_LAST);
        assertEquals(CLIENT_ACCOUNT_2.getContact(), CLIENT_NAME_FIRST_MIDDLE_LAST);
    }

    @Test
    public void testSetContact() throws Exception {
        CLIENT_ACCOUNT_1.setContact(new Name("NewLast", "NewFirst"));
        assertTrue(CLIENT_ACCOUNT_1.getContact().getFirstName().compareTo("NewFirst") == 0);
        assertTrue(CLIENT_ACCOUNT_1.getContact().getLastName().compareTo("NewLast") == 0);
    }
}
