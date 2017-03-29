package modelCollections;

import model.Product;
import model.connection.DBConnect;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rajmu on 17.03.29.
 */
public class DBProductsTest {
    private DBProducts products;

    @Before
    public void setUp() throws Exception {
        products = new DBProducts();
    }

    @Test
    public void getAllTest() throws Exception {
        List<Product> p = products.getAll();
        assertNotEquals("Empty array or connection failed", 0, p.size());
    }
}