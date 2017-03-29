package modelCollections;

import model.connection.IDataAccessObject;
import model.entity.Supplier;

import java.util.List;

/**
 * Created by rajmu on 17.03.28.
 */
public class DBSuppliers implements IDataAccessObject<Supplier> {

    private List<Supplier> suppliers;

    @Override
    public List<Supplier> getAll() {
        return null; //TODO: to be implemented
    }

    @Override
    public Supplier getById(int id) {
        return null; //TODO: to be implemented
    }

    @Override
    public void create(Supplier object) {
        //TODO: to be implemented
    }

    @Override
    public void update(Supplier object) {
        //TODO: to be implemented
    }

    @Override
    public void delete(Supplier object) {
        //TODO: to be implemented
    }
}
