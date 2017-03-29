package modelCollections;

import model.entity.Customer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

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

        assertTrue(list.size() != 0);
    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

}