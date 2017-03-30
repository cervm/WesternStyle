package modelCollections;

import model.Order;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Rajmund Staniek on 30-Mar-17.
 */
public class DBOrdersTest {
    private static DBOrders dbOrders;

    @BeforeClass
    public static void setUp() throws Exception {
        dbOrders = new DBOrders();
    }

    @Test
    public void getAll() throws Exception {
        dbOrders.getAll();

        Field f = dbOrders.getClass().getDeclaredField("orders");
        f.setAccessible(true);
        ArrayList<Order> list = (ArrayList<Order>) f.get(dbOrders);

        assertNotEquals("Empty table or fetching error", 0, list.size());
    }

    @Test
    public void getById() throws Exception {
        Order order = dbOrders.getById(1);
        assertEquals("Empty table or fetching error", 23, order.getAmount(), 2);
    }

    @Test
    public void createDelete() throws Exception {
        List<Order> list = dbOrders.getAll();
        int initialSize = list.size();

        // Create new object
        Order temp = new Order(1, new Date(134275623), new Date(143275623), 2, false, 21);
        temp = dbOrders.create(temp);
        int tempId = temp.getId();

        // Check the list size after creation
        list = dbOrders.getAll();
        int creationSize = list.size();

        // Delete the object
        dbOrders.delete(temp);

        // Check the list size after deletion
        list = dbOrders.getAll();
        int deletionSize = list.size();

        assertNotEquals("The order not created", creationSize, initialSize);
        assertEquals("The order not deleted. The id is " + tempId, deletionSize, initialSize);
    }

    @Test
    public void update() throws Exception {

    }
}