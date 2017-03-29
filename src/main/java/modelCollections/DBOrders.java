package modelCollections;

import model.connection.IDataAccessObject;
import model.Order;

import java.util.List;

/**
 * Orders Data Access Object
 */
public class DBOrders implements IDataAccessObject<Order> {
    private List<Order> orders;

    @Override
    public List<Order> getAll() {
        return null; //TODO: to be implemented
    }

    @Override
    public Order getById(int id) {
        return null; //TODO: to be implemented
    }

    @Override
    public void create(Order... objects) {
        for (int i = 0; i <= objects.length; i++) {

        }
        //TODO: to be implemented
    }

    @Override
    public void update(Order... objects) {
        for (int i = 0; i <= objects.length; i++) {

        }
        //TODO: to be implemented
    }

    @Override
    public void delete(Order... objects) {
        for (int i = 0; i <= objects.length; i++) {

        }
        //TODO: to be implemented
    }
}
