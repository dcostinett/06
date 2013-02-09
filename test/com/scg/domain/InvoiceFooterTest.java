package com.scg.domain;

import org.junit.Test;

import java.util.Scanner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/21/13
 * Time: 7:44 PM
 */
public class InvoiceFooterTest {

    @Test
    public void testIncrementPageNumber() throws Exception {
        String businessName = "MyBusiness";
        InvoiceFooter footer = new InvoiceFooter(businessName);

        //there's a number f ways to do thi... tokenize and grab last token also comes to mind,
        // but I don't know if one way is inherently better than another.
        Scanner scanner = new Scanner(footer.toString());
        scanner.next();
        scanner.next();
        int pageNumber = scanner.nextInt();
        assertEquals(1, pageNumber);
        footer.incrementPageNumber();

        scanner = new Scanner(footer.toString());
        scanner.next();
        scanner.next();

        pageNumber = scanner.nextInt();
        assertEquals(2, pageNumber);
    }


    @Test
    public void testToString() throws Exception {
        String businessName = "MyBusiness";
        InvoiceFooter footer = new InvoiceFooter(businessName);
        assertTrue(footer.toString().trim().startsWith(businessName));
        assertTrue(footer.toString().endsWith("=\n"));
    }
}
