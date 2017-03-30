package modelCollections;

import model.Product;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rajmu on 17.03.30.
 */
public class DBProductsTest {
    private static DBProducts dbProducts;

    @BeforeClass
    public static void setUp() throws Exception {
        dbProducts = new DBProducts();
    }

    @Test
    public void getAll() throws Exception {
        List<Product> p = dbProducts.getAll();
        assertNotEquals(0, p.size());
    }

    @Test
    public void getById() throws Exception {
        Product p = dbProducts.getById(10);
        assertEquals("ad", p.getName());
    }

    @Test
    public void createDelete() throws Exception {
        List<Product> c = dbProducts.getAll();
        int numOfRows = c.size();
        Product temp = new Product() //TODO: needs to be finished
        temp = dbProducts.create(temp);
        System.out.println("The id is: " + temp.getId());
        c = dbProducts.getAll();

        int size = c.size();
        dbProducts.delete(temp);
        assertNotEquals("Didn't create a new row", size, numOfRows);
    }

    @Test
    public void update() throws Exception {

    }
}