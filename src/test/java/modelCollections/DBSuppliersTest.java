package modelCollections;

import model.entity.Supplier;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Rajmund Staniek on 30-Mar-17.
 */
public class DBSuppliersTest {
    private static DBSuppliers dbSuppliers;

    @BeforeClass
    public static void setUp() throws Exception {
        dbSuppliers = new DBSuppliers();
    }

    @Test
    public void getAll() throws Exception {
        dbSuppliers.getAll();

        Field f = dbSuppliers.getClass().getDeclaredField("suppliers");
        f.setAccessible(true);
        ArrayList<Supplier> list = (ArrayList<Supplier>) f.get(dbSuppliers);

        assertNotEquals("Empty table or fetching error", 0, list.size());
    }

    @Test
    public void getById() throws Exception {
        Supplier supplier = dbSuppliers.getById(1);
        assertEquals("Wrong object fetched", "16234", supplier.getCompanyRegNo());
        supplier = dbSuppliers.getById(3);
        assertEquals("Wrong object fetched", "56234", supplier.getCompanyRegNo());
    }

    @Test
    public void createDelete() throws Exception {
        List<Supplier> s = dbSuppliers.getAll();
        int numOfRows = s.size();
        Supplier supplier = new Supplier("Sennheiser", "2334634234", "tes345w2354235", "sdjfhasf", "sdr232", "asas", "DK", "2343");
        supplier = dbSuppliers.create(supplier);
        System.out.println("The ID of supplier: " + supplier.getId());
        s = dbSuppliers.getAll();

        int size = s.size();
        dbSuppliers.delete(supplier);
        assertEquals(numOfRows, size);
    }

    @Test
    public void update() throws Exception {
        String postCode = "2137";
        dbSuppliers.load();
        Supplier s = dbSuppliers.getById(3);
        s.setPostcode(postCode);
        dbSuppliers.update(s);
        dbSuppliers.load();
        assertEquals(postCode, dbSuppliers.getById(3).getPostcode());
    }

}