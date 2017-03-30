package modelCollections;

import model.CustomerGroup;
import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.entity.Customer;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Customers Data Access Object
 */
public class DBCustomers implements IDataAccessObject<Customer> {
    private static final String SELECT_CONTACT_ID = "(SELECT [contact_detail_id] FROM customers WHERE [id] = ?)";
    private ArrayList<Customer> customers;
    private DBConnect dbConnect;
    private boolean isLoaded;
    private ArrayList<CustomerGroup> customerGroups;

    public DBCustomers() throws ModelSyncException {
        customers = new ArrayList<>();
        customerGroups = new ArrayList<>();
        isLoaded = false;
    }

    void load() throws ModelSyncException {
        customers = new ArrayList<>();
        try {
            dbConnect = new DBConnect();
            String query = "SELECT c.id, name, phone, email, address, postcode, city, country_code, customer_group_id\n" +
                    "FROM customers AS c\n" +
                    "INNER JOIN contact_details AS cd ON c.contact_detail_id = cd.id;";
            ResultSet rs = dbConnect.getFromDataBase(query);
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("postcode"),
                        rs.getString("city"),
                        rs.getString("country_code"),
                        rs.getInt("customer_group_id")
                ));
            }
            isLoaded = true;
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load customers.", e);
        }

        customerGroups = new ArrayList<>();
        try {
            dbConnect = new DBConnect();
            String query = "SELECT * FROM customer_groups";
            ResultSet rs = dbConnect.getFromDataBase(query);
            while (rs.next()) {
                customerGroups.add(new CustomerGroup(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("discount")
                ));
            }
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load customer groups.", e);
        }
    }

    @Override
    public List<Customer> getAll() throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        return customers;
    }

    /**
     * Method to get a customer by id
     *
     * @param id id of the customer
     * @return customer with the id
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public Customer getById(int id) throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        return customers.stream().filter(o -> o.getId() == id).findFirst().get();
    }

    /**
     * Method to persist a customer in the database
     *
     * @param customer customer to be persisted
     * @return customer with assigned id from the database
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public Customer create(Customer customer) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();

            //creating contact details record
            String contactQuery = "INSERT INTO [contact_details] ([name], [phone], [email], [address], [postcode], [city], [country_code])\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(contactQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPostcode());
            stmt.setString(6, customer.getCity());
            stmt.setString(7, customer.getCountry());
            stmt.executeUpdate();

            int contactId;
            //fetching contact details data
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contactId = generatedKeys.getInt(1);
                } else {
                    throw new ModelSyncException("Creating contact details failed. No ID retrieved!");
                }
            }

            //create customer
            String customerQuery = "INSERT INTO [customers] ([contact_detail_id], [customer_group_id]) VALUES (?,?);";
            stmt = dbConnect.getConnection().prepareStatement(customerQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, contactId);
            stmt.setInt(2, customer.getGroupID());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                } else {
                    throw new ModelSyncException("Creating contact details failed. No ID retrieved!");
                }
            }

        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("WARNING! Error occurred while creating a new contact details record.", e);
        } finally {
            customers.add(customer);
        }
        return customer;

    }

    /**
     * Method to update a customer in the database
     *
     * @param customer customer to be updated
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public void update(Customer customer) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                    "UPDATE [contact_details]\n" +
                            "SET [phone] = ?, [email] = ?, [address] = ?, [postcode] = ?, [city] = ?, [country_code] = ?\n" +
                            "WHERE [id] =  " + SELECT_CONTACT_ID + ";"
            );
            stmt.setString(1, customer.getPhone());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPostcode());
            stmt.setString(5, customer.getCity());
            stmt.setString(6, customer.getCountry());
            stmt.setInt(7, customer.getId());
            dbConnect.uploadSafe(stmt);

            stmt = dbConnect.getConnection().prepareStatement(
                    "UPDATE [customers]\n" +
                            "SET [customer_group_id] = ?\n" +
                            "WHERE [id] =  ?;"
            );
            stmt.setInt(1, customer.getGroupID());
            stmt.setInt(2, customer.getId());
            dbConnect.uploadSafe(stmt);
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("WARNING! Could not update the customer of id [" + customer.getId() + "]!", e);
        } finally {
            customers.removeIf(c -> c.getId() == customer.getId());
            customers.add(customer);
        }
    }

    /**
     * Method to delete a customer from the database
     *
     * @param customer customer to be deleted
     */
    @Override
    public void delete(Customer customer) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                    "DELETE FROM [contact_details]\n" +
                            "WHERE [id] =  " + SELECT_CONTACT_ID + ";"
            );
            stmt.setInt(1, customer.getId());
            stmt.execute();
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not delete the user!", e);
        } finally {
            customers.removeIf(c -> c.getId() == customer.getId());
        }
    }

    public ArrayList<CustomerGroup> getCustomerGroups() throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        return customerGroups;
    }

    public void assignToGroup(Customer customer, CustomerGroup customerGroup) throws ModelSyncException {
        customer.setGroupID(customerGroup.getGid());
        update(customer);
    }
}
