package modelCollections;

import model.CustomerGroup;
import model.entity.Customer;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
        Customer c = customers.getById(22);
        assertEquals(1, c.getGroupID());
    }

    @Test
    public void createDelete() throws Exception {
        List<Customer> c = customers.getAll();
        int numOfRows = c.size();
        Customer temp = new Customer("test", "tefgst", "test1", "test", "test", "test", "PL", 1);
        temp = customers.create(temp);
        System.out.println("The id is: " + temp.getId());
        c = customers.getAll();

        int size = c.size();
        customers.delete(temp);
        assertNotEquals("Didn't create a new row", size, numOfRows);
    }

    @Test
    public void update() throws Exception {
        String street = "308 Negro Arroyo Lane";
        customers.load();
        Customer c = customers.getById(24);
        c.setAddress(street);
        customers.update(c);
        customers.load();
        assertEquals(street, customers.getById(24).getAddress());
    }

    @Test
    public void updateMultiple() throws Exception {
        String phone = "721312709";
        String city = "Zbonszynek";
        customers.load();

        Customer c = customers.getById(21);
        c.setPhone(phone);
        c.setCity(city);

        customers.update(c);

        DBCustomers c2 = new DBCustomers();

        assertEquals(phone, c2.getById(21).getPhone());
        assertEquals(city, c2.getById(21).getCity());
    }

    @Test
    public void getCustomerGroupsTest() throws Exception {
        ArrayList<CustomerGroup> groups = customers.getCustomerGroups();
        assertNotEquals(0, groups.size());
    }

    @Test
    public void assignToGroupTest() throws Exception {
        int newGroupID = 2;
        customers.load();
        Customer c = customers.getById(24);
        customers.assignToGroup(c, customers.getCustomerGroups().get(1));

        assertEquals(newGroupID, c.getGroupID());
    }
}