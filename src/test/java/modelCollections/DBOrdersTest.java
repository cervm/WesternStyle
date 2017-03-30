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
        List<Order> o = dbOrders.getAll();
        int numOfRows = o.size();
        Order order = new Order(1, new Date(134275623), new Date(143275623), 2, false, 21);
        order = dbOrders.create(order);
        System.out.println("The order ID: " + order.getId());
        o = dbOrders.getAll();

        int size = o.size();
        dbOrders.delete(order);
        System.out.println("Before: " + numOfRows + "\n After" + size);
        assertNotEquals(numOfRows, size);
    }

    @Test
    public void update() throws Exception {

    }
}