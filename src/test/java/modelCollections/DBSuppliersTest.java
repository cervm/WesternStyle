package modelCollections;

import model.entity.Supplier;
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