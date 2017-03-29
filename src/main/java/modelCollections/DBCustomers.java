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
 * Created by rajmu on 17.03.28.
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
            String query = "SELECT [id], [phone], [email], [address], [postcode], [city], [country], [customers.id], [customers.name], [customers.group_id] FROM [contact_details] INNER JOIN [customers] ON [customers.contact_detail_id] = [contact_details.id]";
            ResultSet rs = dbConnect.getFromDataBase(query);
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("postcode"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getInt("customers.id"),
                        rs.getString("customers.name"),
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
        return customers.stream().filter(o -> o.getCid() == id).findFirst().get();
    }

    @Override
    public void create(Customer... objects) throws ModelSyncException {
        for (Customer object : objects) {
            try {
                dbConnect = new DBConnect();

                //creating contact details record
                PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                        "INSERT INTO [contact_details] ([phone], [email], [address], [postcode], [city], [country_code]) VALUES (?,?,?,?,?,?);");
                stmt.setString(1, object.getPhone());
                stmt.setString(2, object.getEmail());
                stmt.setString(3, object.getAddress());
                stmt.setString(4, object.getPostcode());
                stmt.setString(5, object.getCity());
                stmt.setString(6, object.getCountry());
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
                        "INSERT INTO [customers] ([name], [contact_detail_id], [customer_group_id]) VALUES (?,?,?);");
                stmt.setString(1, object.getName());
                stmt.setInt(2, object.getContactId());
                stmt.setInt(3, object.getGroupID());
                dbConnect.uploadSafe(stmt);

                //fetch customer ID
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        object.setCid(generatedKeys.getInt("id"));
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
                        "UPDATE [customers] SET [name]=?, [customer_group_id]=? WHERE [id]=?;");
                stmt.setString(1, object.getName());
                stmt.setInt(2, object.getGroupID());
                stmt.setInt(3, object.getCid());
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
                throw new ModelSyncException("WARNING! Could not update the customer of id [" + object.getCid() + "]!", e);
            } finally {
                customers.removeIf(c -> c.getCid() == object.getCid());
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
                stmt.setInt(1, object.getCid());
                stmt.setInt(2, object.getGroupID());
            } catch (ConnectionException | SQLException e) {
                throw new ModelSyncException("Could not delete the user!", e);
            } finally {
                customers.removeIf(c -> c.getCid() == object.getCid());
            }
        }
    }
}
