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
    private List<Customer> customers;
    private DBConnect dbConnect;

    public DBCustomers() throws ModelSyncException {
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
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load customers.", e);
        }
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }

    @Override
    public Customer getById(int id) {
        return null; //TODO: to be implemented
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
