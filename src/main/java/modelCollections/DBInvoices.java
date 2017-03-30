package modelCollections;

import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.Invoice;
import model.entity.Customer;
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
    public Invoice create(Invoice invoice) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                    "INSERT INTO [invoices] ([payment_date], [amount]) VALUES (?, ?);"
            );
            stmt.setDate(1,invoice.getPaymentDate());
            stmt.setDouble(2,invoice.getAmount());

        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    @Override
    public void update(Invoice invoice) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement ps = dbConnect.getConnection().prepareStatement(
                    "UPDATE [invoices]\n" +
                            "SET [id] = ?, [payment_date] = ?, [amount] = ?"
            );
            ps.setInt(1, invoice.getId());
            ps.setDate(2, java.sql.Date.valueOf(invoice.getPaymentDate().toString()));
            ps.setDouble(3, invoice.getAmount());
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("WARNING! Could not update the invoice of id [" + invoice.getId() + "]!", e);
        } finally {
            invoices.removeIf(p -> p.getId() == invoice.getId());
            invoices.add(invoice);
        }
    }

    @Override
    public void delete(Invoice invoice) {
        try {
            dbConnect = new DBConnect();
            PreparedStatement ps = dbConnect.getConnection().prepareStatement(
                    "DELETE FROM invoices\n" +
                            "WHERE id = ?;"
            );
            ps.setInt(1, invoice.getId());
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        } finally {
            invoices.removeIf(p -> p.getId() == invoice.getId());
        }

    }
}