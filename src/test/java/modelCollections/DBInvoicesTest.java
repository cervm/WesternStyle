package modelCollections;

import model.Invoice;
import model.exception.ModelSyncException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void update() throws Exception {
        int amount = 1234;
        invoices.load();
        Invoice invoice = invoices.getById(15);
        invoice.setAmount(amount);
        invoices.update(invoice);
        invoices.load();
        assertEquals(amount, invoices.getById(15).getAmount(), 1);
    }

    @Test(expected = ModelSyncException.class)
    public void getByIdNoSuchElementExceptionTest() throws Exception {
        Invoice i = invoices.getById(-1);
    }

    @Test(expected = NullPointerException.class)
    public void updateNullExceptionTest() throws Exception {
        invoices.update(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateInvalidObjectExceptionTest() throws Exception {
        Invoice i = new Invoice(null, -1);
        invoices.update(i);
    }

    @Test(expected = NullPointerException.class)
    public void createInvalidObjectEceptionTest() throws Exception {
        Invoice i = new Invoice(new Date(-1), -1);
        invoices.create(i);
    }
}