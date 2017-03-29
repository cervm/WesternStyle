package modelCollections;

import model.connection.IDataAccessObject;
import model.Invoice;

import java.util.List;

/**
 * Invoices Data Access Object
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
    public void create(Invoice... objects) {
        for (int i = 0; i <= objects.length; i++) {

        }
        //TODO: to be implemented
    }

    @Override
    public void update(Invoice... objects) {
        for (int i = 0; i <= objects.length; i++) {

        }
        //TODO: to be implemented
    }

    @Override
    public void delete(Invoice... objects) {
        for (int i = 0; i <= objects.length; i++) {

        }
        //TODO: to be implemented
    }
}