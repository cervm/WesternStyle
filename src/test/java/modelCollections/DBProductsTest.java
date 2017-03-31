package modelCollections;

import model.Category;
import model.Product;
import model.exception.ModelSyncException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for Products DAO
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
        double price = 69;
        products.load();
        Product p = products.getById(5);
        p.setCostPrice(price);
        products.update(p);
        products.load();
        assertEquals(price, products.getById(5).getCostPrice(), 1);
    }

    @Test
    public void getByCategoryTest() throws Exception {
        Category category = new Category(4, "Tools", null, null);
        List<Product> p = products.getByCategory(category);

        assertNotEquals(0, p.size());
    }

    @Test(expected = ModelSyncException.class)
    public void getByIdInvalidIndexExceptionTest() throws Exception {
        products.getById(-1);
    }

    @Test(expected = NullPointerException.class)
    public void getByCategoryInvalidIndeExceptionTest() throws Exception {
        products.getByCategory(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateNullObjectTest() throws Exception {
        products.update(null);
    }

    @Test(expected = ModelSyncException.class)
    public void createInvalidObjectTest() throws Exception {
        Product p = new Product(5, null, null, "asdfasdf", 0, -1, -1, -1);
        products.create(p);
    }
}