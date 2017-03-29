package modelCollections;

import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.entity.Customer;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

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
            String query = "SELECT id, phone, email, address, postcode, city, country, customers.id, customers.name, customers.group_id FROM contact_details INNER JOIN customers ON customers.contact_detail_id = contact_details.id";
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
    public void create(Customer object) {

        //TODO: to be implemented
    }

    @Override
    public void update(Customer object) {
        //TODO: to be implemented
    }

    @Override
    public void delete(Customer object) {
        //TODO: to be implemented
    }
}
