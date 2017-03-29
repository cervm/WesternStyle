package modelCollections;

import model.connection.IDataAccessObject;
import model.Order;

import java.util.List;

/**
 * Created by Ondřej Soukup on 28.03.2017.
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
    public void create(Order object) {
        //TODO: to be implemented
    }

    @Override
    public void update(Order object) {
        //TODO: to be implemented
    }

    @Override
    public void delete(Order object) {
        //TODO: to be implemented
    }
}
