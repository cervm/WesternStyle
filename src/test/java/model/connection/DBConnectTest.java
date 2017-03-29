package model.connection;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Rajmund Staniek on 29-Mar-17.
 */
public class DBConnectTest {
    private DBConnect connect;

    @BeforeClass
    public void setUp() throws Exception {
        connect = new DBConnect();
    }

    @Test
    public void testConstructorJSON() throws Exception {
        DBConnect dbConnect = new DBConnect();
    }

    @Test
    public void testConnection() throws Exception {

    }
}