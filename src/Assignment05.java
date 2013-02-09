import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;
import com.scg.util.DateRange;
import com.scg.util.TimeCardListUtil;
import junit.framework.Assert;

/**
 * Assignment 05 application.
 * Requirements

 You will need to provide two programs to verify your classes. The InitLists class is to be executed first to perform
 the following:

 Create the Consultants, Clients and TimeCards as in previous assignments.
 Serialize the client and timecard lists into files; ClientList.ser, and TimeCardList.ser respectively.
 The Assignment05 class is to be executed second to perform the following:

 Deserialize the lists from the files created.
 Create the invoices, one for each of the clients, using serialization to read the lists from file.
 */
public final class Assignment05 {
    /** The invoice month. */
    private static final int INVOICE_MONTH = Calendar.MARCH;

    /** The test year. */
    private static final int INVOICE_YEAR = 2006;

    /** This class' logger. */
    private static final Logger LOG = Logger.getLogger("Assignment05");

    private List<ClientAccount> myAccounts = new ArrayList<ClientAccount>();
    private List<Consultant> myConsultants = new ArrayList<Consultant>();
    private List<TimeCard> myTimeCards = new ArrayList<TimeCard>();

    /**
     * Prevent instantiation.
     */
    private Assignment05() {
    }

    /**
     * De-serialize the client and time card lists.
     */
    @SuppressWarnings({"serial", "unchecked"})
    public void deSerializeLists() {
        try {
            FileInputStream fin = new FileInputStream("ClientList.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fin);


            myAccounts = (List<ClientAccount>) objectInputStream.readObject();
            objectInputStream.close();

            fin = new FileInputStream("TimeCardList.ser");
            objectInputStream = new ObjectInputStream(fin);

            myTimeCards = (List<TimeCard>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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
        // Print them
        ListFactory.printTimeCards(timeCards, System.out);

        Assignment05 assignment05 = new Assignment05();
        assignment05.deSerializeLists();

        Assert.assertEquals(accounts.size(), assignment05.myAccounts.size());
        Assert.assertEquals(timeCards.size(), assignment05.myTimeCards.size());

        for (int i = 0; i < accounts.size(); i++) {
            Assert.assertEquals(accounts.get(i), assignment05.myAccounts.get(i));
        }

        for (int i = 0; i < timeCards.size(); i++) {
            Assert.assertEquals(timeCards.get(i), assignment05.myTimeCards.get(i));
        }
    }
}
