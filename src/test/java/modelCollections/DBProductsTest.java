package modelCollections;

import model.Product;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rajmu on 17.03.30.
 */
public class DBProductsTest {
    private static DBProducts products;

    @BeforeClass
    public static void setUp() throws Exception {
        products = new DBProducts();
    }

    @Test
    public void getAll() throws Exception {
        List<Product> p = products.getAll();
        assertNotEquals(0, p.size());
    }

    @Test
    public void getById() throws Exception {
        Product p = products.getById(10);
        assertEquals("ad", p.getName());
    }

    @Test
    public void createDelete() throws Exception {
        // Initial list size
        List<Product> list = products.getAll();
        int initialSize = list.size();

        // Create new object
        Product temp = new Product(5, "Product name", "DK", "Description", 100, 150, 50, 1);
        temp = products.create(temp);
        int tempId = temp.getId();

        // Check the list size after creation
        list = products.getAll();
        int creationSize = list.size();

        // Delete the object
        products.delete(temp);

        // Check the list size after deletion
        list = products.getAll();
        int deletionSize = list.size();

        assertNotEquals("The product not created", creationSize, initialSize);
        assertEquals("The product not deleted. The id is " + tempId, deletionSize, initialSize);
    }

    @Test
    public void update() throws Exception {

    }
}