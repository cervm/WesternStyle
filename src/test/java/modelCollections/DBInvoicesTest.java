package modelCollections;

import model.Invoice;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Rajmund Staniek on 30-Mar-17.
 */
public class DBInvoicesTest {
    private static DBInvoices dbInvoices;

    @BeforeClass
    public static void setUp() throws Exception {
        dbInvoices = new DBInvoices();
    }

    @Test
    public void getAll() throws Exception {
        dbInvoices.load();

        Field f = dbInvoices.getClass().getDeclaredField("invoices");
        f.setAccessible(true);
        ArrayList<Invoice> list = (ArrayList<Invoice>) f.get(dbInvoices);

        assertNotEquals("Empty table or fetching error", 0, list.size());
    }

    @Test
    public void getById() throws Exception {
        Invoice invoice = dbInvoices.getById(1);
        assertEquals("Wrong object fetched", 2137, invoice.getAmount(), 1);
    }

    @Ignore("Not implemented")
    @Test
    public void create() throws Exception {

    }

    @Ignore("Not implemented")
    @Test
    public void update() throws Exception {

    }
    
    @Ignore("Not implemented")
    @Test
    public void delete() throws Exception {

    }

}