package modelCollections;

import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.entity.Customer;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Customers Data Access Object
 */
public class DBCustomers implements IDataAccessObject<Customer> {
    private ArrayList<Customer> customers;
    private DBConnect dbConnect;
    private boolean isLoaded;

    public DBCustomers() throws ModelSyncException {
        customers = new ArrayList<>();
        isLoaded = false;
    }

    public void load() throws ModelSyncException {
        customers = new ArrayList<>();
        try {
            dbConnect = new DBConnect();
            String query = "SELECT c.id, name, phone, email, address, postcode, city, country_code, customer_group_id\n" +
                    "FROM customers AS c\n" +
                    "INNER JOIN contact_details AS cd ON c.contact_detail_id = cd.id;";
            ResultSet rs = dbConnect.getFromDataBase(query);
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("c.id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("postcode"),
                        rs.getString("city"),
                        rs.getString("country_code"),
                        rs.getInt("customers.group_id")
                ));
            }
            isLoaded = true;
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load customers.", e);
        }
    }

    @Override
    public List<Customer> getAll() throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        return customers;
    }

    @Override
    public Customer getById(int id) throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        if (id >= customers.size() || id < 0) {
            throw new ModelSyncException("ID is out of range!");
        }
        return customers.stream().filter(o -> o.getId() == id).findFirst().get();
    }

    @Override
    public void create(Customer... objects) throws ModelSyncException {
        for (Customer object : objects) {
            try {
                dbConnect = new DBConnect();

                //creating contact details record
                PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                        "INSERT INTO [contact_details] ([name], [phone], [email], [address], [postcode], [city], [country_code]) VALUES (?,?,?,?,?,?,?);");
                stmt.setString(1, object.getName());
                stmt.setString(2, object.getPhone());
                stmt.setString(3, object.getEmail());
                stmt.setString(4, object.getAddress());
                stmt.setString(5, object.getPostcode());
                stmt.setString(6, object.getCity());
                stmt.setString(7, object.getCountry());
                dbConnect.uploadSafe(stmt);

                //fetching contact details data
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        object.setContactId(generatedKeys.getInt("id"));
                    } else {
                        throw new ModelSyncException("Creating contact details failed. No ID retrieved!");
                    }
                }

                //create customer
                stmt = dbConnect.getConnection().prepareStatement(
                        "INSERT INTO [customers] ([contact_detail_id], [customer_group_id]) VALUES (?,?);");
                stmt.setInt(1, object.getContactId());
                stmt.setInt(2, object.getGroupID());
                dbConnect.uploadSafe(stmt);

                //fetch customer ID
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        object.setId(generatedKeys.getInt("id"));
                    } else {
                        throw new ModelSyncException("Creating customer failed. No ID retrieved!");
                    }
                }
            } catch (ConnectionException | SQLException e) {
                throw new ModelSyncException("WARNING! Error occured while creating a new contact details record.", e);
            } finally {
                customers.add(object);
            }
        }

    }

    @Override
    public void update(Customer... objects) throws ModelSyncException {
        for (Customer object : objects) {
            try {
                dbConnect = new DBConnect();
                PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                        "UPDATE [customers] SET [customer_group_id]=? WHERE [id]=?;");
                stmt.setInt(1, object.getGroupID());
                stmt.setInt(2, object.getId());
                dbConnect.uploadSafe(stmt);

                stmt = dbConnect.getConnection().prepareStatement(
                        "UPDATE [contact_details] SET [phone]=?, [email]=?, [address]=?, [postcode]=?, [city]=?, [country_code]=? WHERE id=?;");
                stmt.setString(1, object.getPhone());
                stmt.setString(2, object.getEmail());
                stmt.setString(3, object.getAddress());
                stmt.setString(4, object.getPostcode());
                stmt.setString(5, object.getCity());
                stmt.setString(6, object.getCountry());
                stmt.setInt(7, object.getGroupID());
                dbConnect.uploadSafe(stmt);
            } catch (ConnectionException | SQLException e) {
                throw new ModelSyncException("WARNING! Could not update the customer of id [" + object.getId() + "]!", e);
            } finally {
                customers.removeIf(c -> c.getId() == object.getId());
                customers.add(object);
            }
        }
    }

    @Override
    public void delete(Customer... objects) throws ModelSyncException {
        for (Customer object : objects) {
            try {
                dbConnect = new DBConnect();
                PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                        "DELETE FROM [customers] WHERE id=?;" +
                                "DELETE FROM [contact_details] WHERE [id]=?;");
                stmt.setInt(1, object.getId());
                stmt.setInt(2, object.getGroupID());
            } catch (ConnectionException | SQLException e) {
                throw new ModelSyncException("Could not delete the user!", e);
            } finally {
                customers.removeIf(c -> c.getId() == object.getId());
            }
        }
    }
}
