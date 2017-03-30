package modelCollections;

import model.Invoice;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for Invoices DAO
 */
public class DBInvoicesTest {
    private static DBInvoices invoices;

    @BeforeClass
    public static void setUp() throws Exception {
        invoices = new DBInvoices();
    }

    @Test
    public void getAll() throws Exception {
        invoices.load();

        Field f = invoices.getClass().getDeclaredField("invoices");
        f.setAccessible(true);
        ArrayList<Invoice> list = (ArrayList<Invoice>) f.get(invoices);

        assertNotEquals("Empty table or fetching error", 0, list.size());
        ArrayList<Invoice> arrayList = invoices.getAll();
        for (Invoice i : arrayList) {
            System.out.println(i.toString());
        }
    }

    @Test
    public void getById() throws Exception {
        Invoice invoice = invoices.getById(1);
        assertEquals("Wrong object fetched", 2137, invoice.getAmount(), 1);
    }

    @Test
    public void createDelete() throws Exception {
        // Initial list size
        List<Invoice> list = invoices.getAll();
        int initialSize = list.size();

        // Create new object
        Invoice temp = new Invoice(new Date(Calendar.getInstance().getTimeInMillis()), 100);
        temp = invoices.create(temp);
        int tempId = temp.getId();

        // Check the list size after creation
        list = invoices.getAll();
        int creationSize = list.size();

        // Delete the object
        invoices.delete(temp);

        // Check the list size after deletion
        list = invoices.getAll();
        int deletionSize = list.size();

        assertNotEquals("The invoice not created", creationSize, initialSize);
        assertEquals("The invoice not deleted. The id is " + tempId, deletionSize, initialSize);
    }

    @Ignore("Not implemented")
    @Test
    public void update() throws Exception {

    }

}