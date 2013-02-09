package com.scg.domain;

import com.scg.util.Name;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/20/13
 * Time: 5:41 AM
 */
public class ConsultantTest {
    @Test
    public void testGetName() throws Exception {
        Consultant consultant = new Consultant(new Name("Last", "First"));
        Name consultantName = consultant.getName();
        assertTrue(consultantName.equals(new Name("Last", "First")));
    }

    @Test
    public void testToString() throws Exception {
        Name name = new Name("Last", "First");
        Consultant consultant = new Consultant(name);
        String consultantStr = consultant.toString();
        assertTrue(consultantStr.equals(name.toString()));
    }
}
