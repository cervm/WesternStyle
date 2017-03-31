package modelCollections;

import model.Order;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for Orders DAO
 */
public class DBOrdersTest {
    private static DBOrders orders;

    @BeforeClass
    public static void setUp() throws Exception {
        orders = new DBOrders();
    }

    @Test
    public void getAll() throws Exception {
        orders.getAll();

        Field f = orders.getClass().getDeclaredField("orders");
        f.setAccessible(true);
        ArrayList<Order> list = (ArrayList<Order>) f.get(orders);

        assertNotEquals("Empty table or fetching error", 0, list.size());
    }

    @Test
    public void getById() throws Exception {
        Order order = orders.getById(1);
        assertEquals("Empty table or fetching error", 23, order.getAmount(), 2);
    }

    @Test
    public void createDelete() throws Exception {
        List<Order> list = orders.getAll();
        int initialSize = list.size();

        // Create new object
        Order temp = new Order(1, new Date(134275623), new Date(143275623), 2, false, 21);
        temp = orders.create(temp);
        int tempId = temp.getId();

        // Check the list size after creation
        list = orders.getAll();
        int creationSize = list.size();

        // Delete the object
        orders.delete(temp);

        // Check the list size after deletion
        list = orders.getAll();
        int deletionSize = list.size();

        assertNotEquals("The order not created", creationSize, initialSize);
        assertEquals("The order not deleted. The id is " + tempId, deletionSize, initialSize);
    }

    @Test
    public void update() throws Exception {
        boolean status = true;
        orders.load();
        Order order = orders.getById(29);
        order.setDeliveryStatus(status);
        orders.update(order);
        orders.load();
        assertEquals(status, orders.getById(29).getDeliveryStatus());
    }
}