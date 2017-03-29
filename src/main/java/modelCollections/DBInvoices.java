package modelCollections;

import model.connection.IDataAccessObject;
import model.Invoice;

import java.util.List;

/**
 * Created by Ondřej Soukup on 28.03.2017.
 */
public class DBInvoices implements IDataAccessObject<Invoice> {
    private List<Invoice> invoices;

    @Override
    public List<Invoice> getAll() {
        return null; //TODO: to be implemented
    }

    @Override
    public Invoice getById(int id) {
        return null; //TODO: to be implemented
    }

    @Override
    public void create(Invoice object) {
        //TODO: to be implemented
    }

    @Override
    public void update(Invoice object) {
        //TODO: to be implemented
    }

    @Override
    public void delete(Invoice object) {
        //TODO: to be implemented
    }
}