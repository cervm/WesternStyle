package modelCollections;

import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.entity.Supplier;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Suppliers Data Access Object
 */
public class DBSuppliers implements IDataAccessObject<Supplier> {
    private static final String SELECT_CONTACT_ID = "(SELECT [contact_detail_id] FROM customers WHERE [id] = ?)";
    private List<Supplier> suppliers;
    private DBConnect dbConnect;
    private boolean isLoaded;

    DBSuppliers() throws ModelSyncException {
        suppliers = new ArrayList<>();
        isLoaded = false;
    }

    private void load() throws ModelSyncException {
        suppliers = new ArrayList<>();
        try {
            dbConnect = new DBConnect();
            String query = "SELECT s.id, name, phone, email, address, postcode, city, country_code, co_reg_no\n" +
                    "FROM suppliers AS s\n" +
                    "INNER JOIN contact_details AS cd ON s.contact_detail_id = cd.id;";
            ResultSet rs = dbConnect.getFromDataBase(query);
            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("postcode"),
                        rs.getString("city"),
                        rs.getString("country_code"),
                        rs.getString("co_reg_no")
                ));
            }
            isLoaded = true;
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load customers.", e);
        }
    }

    @Override
    public List<Supplier> getAll() throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        return suppliers;
    }

    /**
     * Method to get a supplier by id
     *
     * @param id id of the supplier
     * @return supplier with the id
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public Supplier getById(int id) throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        return suppliers.stream().filter(o -> o.getId() == id).findFirst().get();
    }

    /**
     * Method to persist a supplier in the database
     *
     * @param supplier supplier to be persisted
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public Supplier create(Supplier supplier) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();

            //creating contact details record
            String contactQuery = "INSERT INTO [contact_details] ([name], [phone], [email], [address], [postcode], [city], [country_code])\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(contactQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getPhone());
            stmt.setString(3, supplier.getEmail());
            stmt.setString(4, supplier.getAddress());
            stmt.setString(5, supplier.getPostcode());
            stmt.setString(6, supplier.getCountry());

            stmt.executeUpdate();

            int contactId;
            //fetching contact details data
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contactId = generatedKeys.getInt(1);
                } else {
                    throw new ModelSyncException("Creating contact details failed. No ID retrieved!");
                }
            }

            String supplierQuery = "INSERT INTO [suppliers] ([co_reg_no], [contact_detail_id]) VALUES (?,?);";
            stmt = dbConnect.getConnection().prepareStatement(supplierQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, supplier.getCompanyRegNo());
            stmt.setInt(2, contactId);
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    supplier.setId(generatedKeys.getInt(1));
                } else {
                    throw new ModelSyncException("Creating contact details failed. No ID retrieved!");
                }
            }
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load suppliers.", e);
        }
        return supplier;

    }

    /**
     * Method to update a supplier in the database
     *
     * @param supplier supplier to be updated
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public void update(Supplier supplier) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement updatePs = dbConnect.getConnection().prepareStatement(
                    "UPDATE [contact_details]\n" +
                            "SET [phone] = ?, [email] = ?, [address] = ?, [postcode] = ?, [city] = ?, [country_code] = ?\n" +
                            "WHERE [id] =  " + SELECT_CONTACT_ID + ";"
            );
            updatePs.setString(1, supplier.getName());
            updatePs.setString(2, supplier.getPhone());
            updatePs.setString(3, supplier.getEmail());
            updatePs.setString(4, supplier.getAddress());
            updatePs.setString(5, supplier.getPostcode());
            updatePs.setString(6, supplier.getCountry());
            dbConnect.uploadSafe(updatePs);
            String updateSupplierQuery = "UPDATE suppliers SET co_reg_no = ? WHERE id = ?;";
            PreparedStatement updateSuppPs = dbConnect.getConnection().prepareStatement(updateSupplierQuery);
            updateSuppPs.setString(1, supplier.getCompanyRegNo());
            updateSuppPs.setInt(2, supplier.getId());
            dbConnect.uploadSafe(updateSuppPs);
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not update the supplier.", e);
        }

    }

    /**
     * Method to delete a supplier from the database
     *
     * @param supplier supplier to be deleted
     */
    @Override
    public void delete(Supplier supplier) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                    "DELETE FROM [contact_details]\n" +
                            "WHERE [id] =  " + SELECT_CONTACT_ID + ";"
            );
            stmt.setInt(1, supplier.getId());
            stmt.execute();
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not delete the user!", e);
        } finally {
            suppliers.removeIf(c -> c.getId() == supplier.getId());
        }

    }
}
