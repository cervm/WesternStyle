package modelCollections;

import model.connection.IDataAccessObject;
import model.entity.Customer;

import java.util.List;

/**
 * Created by rajmu on 17.03.28.
 */
public class DBCustomers implements IDataAccessObject<Customer> {
    private List<Customer> customers;

    @Override
    public List<Customer> getAll() {
        return null; //TODO: to be implemented
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
