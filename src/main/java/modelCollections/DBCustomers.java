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
            String query = "SELECT [id], [phone], email, address, postcode, city, country, customers.id, customers.name, customers.group_id FROM contact_details INNER JOIN customers ON customers.contact_detail_id = contact_details.id";
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
        for (int i = 0; i <= objects.length; i++){
            try {
                dbConnect = new DBConnect();

                //creating contact details record
                PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                        "INSERT INTO [contact_details] ([phone], [email], [address], [postcode], [city], [country_code]) VALUES (?,?,?,?,?,?);");
                stmt.setString(1, objects[i].getPhone());
                stmt.setString(2, objects[i].getEmail());
                stmt.setString(3, objects[i].getAddress());
                stmt.setString(4, objects[i].getPostcode());
                stmt.setString(5, objects[i].getCity());
                stmt.setString(6, objects[i].getCountry());
                dbConnect.uploadSafe(stmt);

                //fetching contact details data
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        objects[i].setContactId(generatedKeys.getInt("id"));
                    } else {
                        throw new ModelSyncException("Creating contact details failed. No ID retrieved!");
                    }
                }

                //create customer
                stmt = dbConnect.getConnection().prepareStatement(
                        "INSERT INTO [customers] ([name], [contact_detail_id], [customer_group_id]) VALUES (?,?,?);");
                stmt.setString(1, objects[i].getName());
                stmt.setInt(2, objects[i].getContactId());
                stmt.setInt(3, objects[i].getGroupID());
                dbConnect.uploadSafe(stmt);

                //fetch customer ID
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        objects[i].setCid(generatedKeys.getInt("id"));
                    } else {
                        throw new ModelSyncException("Creating customer failed. No ID retrieved!");
                    }
                }
            } catch (ConnectionException | SQLException e) {
                throw new ModelSyncException("WARNING! Error occured while creating a new contact details record.", e);
            } finally {
                customers.add(objects[i]);
            }
        }

    }

    @Override
    public void update(Customer... objects) throws ModelSyncException {
        for (int i = 0; i <= objects.length; i++){

        }
        //TODO: to be implemented
    }

    @Override
    public void delete(Customer... objects) {
        for (int i = 0; i <= objects.length; i++){

        }
        //TODO: to be implemented
    }
}
