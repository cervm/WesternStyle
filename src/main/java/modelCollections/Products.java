package modelCollections;

import model.connection.IDataAccessObject;
import model.Product;

import java.util.List;

/**
 * Created by Ondřej Soukup on 28.03.2017.
 */
public class Products implements IDataAccessObject<Product> {
    private List<Product> invoices;

    @Override
    public List<Product> getAll() {
        return null; //TODO: to be implemented
    }

    @Override
    public Product getById(int id) {
        return null; //TODO: to be implemented
    }

    @Override
    public void create(Product object) {
        //TODO: to be implemented
    }

    @Override
    public void update(Product object) {
        //TODO: to be implemented
    }

    @Override
    public void delete(Product object) {
        //TODO: to be implemented
    }
}
