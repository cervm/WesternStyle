package modelCollections;

import model.Order;
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