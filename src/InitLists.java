import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;
import com.scg.util.DateRange;
import com.scg.util.TimeCardListUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/27/13
 * Time: 10:32 AM
 */
public class InitLists {
    /** The invoice month. */
    private static final int INVOICE_MONTH = Calendar.MARCH;

    /** The test year. */
    private static final int INVOICE_YEAR = 2006;

    /** This class' logger. */
    private static final Logger LOG = Logger.getLogger("InitLists");


    /**
     * Create invoices for the clients from the timecards.
     *
     * @param accounts the accounts to create the invoices for
     * @param timeCards the time cards to create the invoices from
     *
     * @return the created invoices
     */
    private static List<Invoice> createInvoices(final List<ClientAccount> accounts,
                                                final List<TimeCard> timeCards) {
        List<Invoice> invoices = new ArrayList<Invoice>();

        List<TimeCard> timeCardList = TimeCardListUtil
                .getTimeCardsForDateRange(timeCards, new DateRange(INVOICE_MONTH, INVOICE_YEAR));
        for (ClientAccount account : accounts) {
            Invoice invoice = new Invoice(account, INVOICE_MONTH, INVOICE_YEAR);
            invoices.add(invoice);
            for (TimeCard currentTimeCard : timeCardList) {
                invoice.extractLineItems(currentTimeCard);
            }
        }

        return invoices;
    }


    /**
     * Serialize the ClientList to the file name provided
     * @param timeCards
     */
    private static void serializeTimeCardList(List<TimeCard> timeCards, String fileName) throws Exception {
        FileOutputStream fout = new FileOutputStream(fileName);
        ObjectOutputStream objOut = new ObjectOutputStream(fout);

        objOut.writeObject(timeCards);
        objOut.close();
    }

    /**
     * Serialize the ClientList to the file name provided
     * @param consultants
     */
    private static void serializeConsultantList(List<Consultant> consultants, String fileName) throws Exception {
        FileOutputStream fout = new FileOutputStream(fileName);
        ObjectOutputStream objOut = new ObjectOutputStream(fout);

        for (Consultant acct : consultants) {
            LOG.info(String.format("Serializing consultant: %s", acct.getName().toString()));
            objOut.writeObject(acct);
        }

        objOut.close();
    }

    /**
     * Serialize the ClientList to the file name provided
     * @param clientAccounts
     */
    private static void serializeClientList(List<ClientAccount> clientAccounts, String fileName) throws Exception {
        FileOutputStream fout = new FileOutputStream(fileName);
        ObjectOutputStream objOut = new ObjectOutputStream(fout);

        objOut.writeObject(clientAccounts);
        objOut.close();
    }

    /**
     * The application method.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        // Create lists to be populated by factory
        List<ClientAccount> accounts = new ArrayList<ClientAccount>();
        List<Consultant> consultants = new ArrayList<Consultant>();
        List<TimeCard> timeCards = new ArrayList<TimeCard>();
        ListFactory.populateLists(accounts, consultants, timeCards);

        try {
            serializeClientList(accounts, "ClientList.ser");
            serializeTimeCardList(timeCards, "TimeCardList.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
