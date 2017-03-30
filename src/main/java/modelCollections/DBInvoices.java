package modelCollections;

import model.Invoice;
import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Invoices Data Access Object
 */
public class DBInvoices implements IDataAccessObject<Invoice> {
    private ArrayList<Invoice> invoices;
    private DBConnect dbConnect;
    private boolean isLoaded;

    public DBInvoices() {
        isLoaded = false;
    }

    public void load() throws ModelSyncException {
        invoices = new ArrayList<>();
        try {
            dbConnect = new DBConnect();
            ResultSet rs = dbConnect.getFromDataBase("SELECT * FROM invoices");
            while (rs.next()) {
                ArrayList<Integer> orders = new ArrayList<>();
                ResultSet orderResults = dbConnect.getFromDataBase("SELECT * FROM orders AS o INNER JOIN invoices AS i ON i.id = o.invoice_id;");
                while (orderResults.next()) {
                    orders.add(orderResults.getInt("id"));
                }
                invoices.add(new Invoice(rs.getInt("id"),
                        rs.getDate("payment_date"),
                        rs.getInt("amount"),
                        orders));
                isLoaded = true;
            }
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load invoices.", e);
        }
    }

    @Override
    public ArrayList<Invoice> getAll() throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        return invoices;
    }

    @Override
    public Invoice getById(int id) throws ModelSyncException {
        Invoice invoice;
        try {
            dbConnect = new DBConnect();
            ResultSet rs = dbConnect.getFromDataBase("SELECT * FROM invoices WHERE id = " + id);
            rs.next();
            ResultSet orderResults = dbConnect.getFromDataBase("SELECT * FROM orders AS o INNER JOIN invoices AS i ON i.id = o.invoice_id;");
            ArrayList<Integer> orders = new ArrayList<>();
            while (orderResults.next()) {
                orders.add(orderResults.getInt("id"));
            }
            invoice = new Invoice(rs.getInt("id"),
                    rs.getDate("payment_date"),
                    rs.getInt("amount"),
                    orders);
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load invoices.", e);
        }
        return invoice;
    }

    @Override
    public Invoice create(Invoice invoice) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            String invoiceQuery = "INSERT INTO [invoices] ([payment_date], [amount]) VALUES (?, ?);";
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(invoiceQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, invoice.getPaymentDate());
            stmt.setDouble(2, invoice.getAmount());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    invoice.setId(generatedKeys.getInt(1));
                } else {
                    throw new ModelSyncException("Creating invoice failed. No ID retrieved!");
                }
            }
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        } finally {
            invoices.add(invoice);
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