package model.connection;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Rajmund Staniek on 29-Mar-17.
 */
public class DBConnectTest {
    private static DBConnect connect;

    @BeforeClass
    public static void setUp() throws Exception {
        connect = new DBConnect();
    }

    @Test
    public void testConstructorJSON() throws Exception {
        DBConnect dbConnect = new DBConnect();
    }

    @Test
    public void testConnection() throws Exception {
        assertFalse(connect.testConnection(" ", "", ""));
    }

    @Test
    public void dataRetrieveTest() throws Exception {
        ResultSet rs = connect.getFromDataBase("SELECT * FROM countries");
        rs.next();
        String result = rs.getString("code");

        assertEquals("Wrong country code", "AD", result);
    }
}