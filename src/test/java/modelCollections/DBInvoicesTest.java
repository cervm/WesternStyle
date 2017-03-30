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
 * Created by Rajmund Staniek on 30-Mar-17.
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
    public void create() throws Exception {
        List<Invoice> i = invoices.getAll();
        int numOfRows = i.size();
        Invoice temp = new Invoice(new Date(Calendar.getInstance().getTimeInMillis()), 100);
        temp = invoices.create(temp);
        System.out.println("The id is: " + temp.getId());
        i = invoices.getAll();

        int size = i.size();
        invoices.delete(temp);
        assertNotEquals("Didn't create a new row", size, numOfRows);
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