package modelCollections;

import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.Invoice;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Invoices Data Access Object
 */
public class DBInvoices implements IDataAccessObject<Invoice> {
    private DBConnect dbConnect;

    @Override
    public List<Invoice> getAll() throws ModelSyncException {
        List<Invoice> invoices = new LinkedList<>();
        try{
            dbConnect = new DBConnect();
            ResultSet rs = dbConnect.getFromDataBase("SELECT * FROM invoices");
            while(rs.next())
                invoices.add(new Invoice(rs.getInt("id"),
                                        rs.getDate("payment_date"),
                                        rs.getInt("amount"),
                                        rs.getInt("order_id")));
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load invoices.", e);
        }
        return invoices;
    }

    @Override
    public Invoice getById(int id) throws ModelSyncException {
        Invoice invoice;
        try{
            dbConnect = new DBConnect();
            ResultSet rs = dbConnect.getFromDataBase("SELECT * FROM invoice WHERE id = " + id);
            while(rs.next())
                invoice = new Invoice(rs.getInt("id"),
                        rs.getDate("payment_date"),
                        rs.getInt("amount"),
                        rs.getInt("order_id"));
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load invoices.", e);
        }
        return invoice;
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