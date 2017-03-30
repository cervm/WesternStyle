package modelCollections;

import model.entity.Customer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by rajmu on 17.03.29.
 */
public class DBCustomersTest {
    private static DBCustomers customers;

    @BeforeClass
    public static void setUp() throws Exception {
        customers = new DBCustomers();
    }

    @Test
    public void load() throws Exception {
        customers.load();

        Field array = customers.getClass().getDeclaredField("customers");
        array.setAccessible(true);
        ArrayList<Customer> list = (ArrayList<Customer>) array.get(customers);

        assertNotEquals("Empty table or fetching error", 0, list.size());
    }

    @Test
    public void getAll() throws Exception {
        List<Customer> c = customers.getAll();
        assertNotEquals("Empty table or fetching error", 0, c.size());
    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void create() throws Exception {
        List<Customer> c = customers.getAll();
        int numOfRows = c.size();
        Customer temp = new Customer("test", "tefgst", "test1", "test", "test", "test", "PL", 1);
        temp = customers.create(temp);
        c = customers.getAll();

        customers.delete(temp);
        assertNotEquals("Didn't create a new row", c.size(), numOfRows);

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

}