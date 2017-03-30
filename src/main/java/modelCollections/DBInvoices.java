package modelCollections;

import model.Product;
import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.Invoice;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Invoices Data Access Object
 */
public class DBInvoices implements IDataAccessObject<Invoice> {
    private LinkedList<Invoice> invoices;
    private DBConnect dbConnect;

    @Override
    public List<Invoice> getAll() throws ModelSyncException {
        List<Invoice> invoices = new LinkedList<>();
        try {
            dbConnect = new DBConnect();
            ResultSet rs = dbConnect.getFromDataBase("SELECT * FROM invoices");
            while (rs.next())
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
        try {
            dbConnect = new DBConnect();
            ResultSet rs = dbConnect.getFromDataBase("SELECT * FROM invoice WHERE id = " + id);
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
    public void create(Invoice... objects) throws ModelSyncException {
        for (Invoice object : objects) {
            try {
                dbConnect = new DBConnect();
                PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(
                        "INSERT INTO [invoices] ([payment_date], [amount])");

            } catch (ConnectionException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Invoice... objects) throws ModelSyncException {
        for (Invoice object : objects) {
            try {
                dbConnect = new DBConnect();
                PreparedStatement ps = dbConnect.getConnection().prepareStatement(
                        "UPDATE [invoices]\n" +
                                "SET [id] = ?, [payment_date] = ?, [amount] = ?"
                );
                ps.setInt(1, object.getId());
                ps.setDate(2, java.sql.Date.valueOf(object.getPaymentDate().toString()));
                ps.setDouble(3, object.getAmount());
            } catch (ConnectionException | SQLException e) {
                throw new ModelSyncException("WARNING! Could not update the invoice of id [" + object.getId() + "]!", e);
            } finally {
                invoices.removeIf(p -> p.getId() == object.getId());
                invoices.add(object);
            }
        }
    }

    @Override
    public void delete(Invoice... objects) {
        for (Invoice object : objects) {
            try {
                dbConnect = new DBConnect();
                PreparedStatement ps = dbConnect.getConnection().prepareStatement(
                        "DELETE FROM invoices\n" +
                                "WHERE id = ?;"
                );
                ps.setInt(1, object.getId());
            } catch (ConnectionException | SQLException e) {
                e.printStackTrace();
            } finally {
                invoices.removeIf(p -> p.getId() == object.getId());
            }

        }
    }
}