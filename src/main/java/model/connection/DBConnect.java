package model.connection;

import javafx.scene.control.TextField;
import model.exception.ConnectionException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.sql.*;


/**
 * Created by Domestos Maximus on 24-Nov-16.
 */
public class DBConnect {

    private static String url;
    private static String username;
    private static String password;

    public DBConnect() {
        //TODO: retrieve data from JSON fole
        try {
            String file = "/data.json";
            FileReader fileReader = new FileReader(file);
            //JSONObject jsonObject = (JSONObject) new JSONParser().parse(fileReader);
            JSONObject jsonObject = new JSONObject(fileReader);
            JSONArray dbArray = jsonObject.getJSONArray("database");
            url = dbArray.getString(0);
            username = dbArray.getString(1);
            password = dbArray.getString(2);
            System.out.println("URL: " + url + '\n' +
                    "User: " + username + '\n' +
                    "Password" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Connects to thee database
     * @return returns connection object
     */
    private static Connection connect(String hostname, String user, String pass) throws ConnectionException {
        Connection con = null;
        try {
            con = DriverManager.getConnection(hostname, user, pass);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new ConnectionException("Exception occured while connecting to the database", ex);
        }
        return con;
    }

    /***
     * Returns the connection object
     * @return Connection
     */
    public Connection getConnection() throws ConnectionException {
        return connect(url, username, password);
    }

    /***
     * Test the connection
     * @return returns True when connected, otherwise false
     */
    public static boolean testConnection(String host, String user, String pass) {
        try (Connection conn = DriverManager.getConnection(host, user, pass)) {
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    /***
     * Executes specified SQL query and returns the data from the table
     */
    public ResultSet getFromDataBase(String query) throws ConnectionException {
        Connection con = connect(url, username, password);
        ResultSet rs = null;
        Statement statement = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(e);
        }
        return rs;
    }

    /***
     * Uploads data stated in the query to the database (UNSAFE)
     * @param query an SQL query string
     */
    public void upload(String query) throws ConnectionException {
        Connection con = connect(url, username, password);
        try {
            Statement statement = con.createStatement();
            statement.execute(query);
            con.close();
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    /***
     * Uploads data stated in the query to the database (SAFE)
     * @param stmt preparedStatement object with protection against SQLi
     */
    public void uploadSafe(PreparedStatement stmt) throws ConnectionException {
        Connection con = connect(url, username, password);
        try {
            stmt.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            throw new ConnectionException("WARNING! exception occured while uploading a query to the server.", ex);
        }
    }

    /***
     *Checks the textField for illegal characters
     * @param tf a TextField object
     * @return true when the TextField object doesn't contain any illegal characters. False otherwise
     */
    public static boolean validateField(TextField tf) {
        //TODO: should be implemented better but didn't have creativity to do it better
        return !(tf.getText().contains(";") || tf.getText().contains("[") || tf.getText().contains("]") || tf.getText().contains("{") || tf.getText().contains("}")) && !tf.getText().isEmpty();
    }
}