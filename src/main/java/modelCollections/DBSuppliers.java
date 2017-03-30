package modelCollections;

import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.entity.Supplier;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Suppliers Data Access Object
 */
public class DBSuppliers implements IDataAccessObject<Supplier> {

    private List<Supplier> suppliers;
    private DBConnect conn;
    private boolean isLoaded;

    public DBSuppliers() throws ModelSyncException {
        suppliers = new ArrayList<>();
        isLoaded = false;
    }

    private void load() throws ModelSyncException {
        suppliers = new ArrayList<>();
        try {
            conn = new DBConnect();
            String query = "SELECT s.id, name, phone, email, address, postcode, city, country_code, co_reg_no\n" +
                    "FROM suppliers AS s\n" +
                    "INNER JOIN contact_details AS cd ON s.contact_detail_id = cd.id;";
            ResultSet rs = conn.getFromDataBase(query);
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
        if (id >= suppliers.size() || id < 0) {
            throw new ModelSyncException("ID is out of range!");
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
            conn = new DBConnect();
            String contactQuery = "INSERT INTO [contact_details] ([phone], [email], [address], [postcode], [city], [country_code]) VALUES (?,?,?,?,?,?);";
            PreparedStatement contactPs = conn.getConnection().prepareStatement(contactQuery);
            contactPs.setString(2, supplier.getPhone());
            contactPs.setString(3, supplier.getEmail());
            contactPs.setString(4, supplier.getAddress());
            contactPs.setString(5, supplier.getPostcode());
            contactPs.setString(6, supplier.getCountry());
            conn.uploadSafe(contactPs);
            try (ResultSet generatedKeys = contactPs.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    supplier.setContactId(generatedKeys.getInt("id"));
                } else {
                    throw new ModelSyncException("Creating contact details failed. No ID retrieved!");
                }
            }
            String supplierQuery = "INSERT INTO [customers] ([name], [co_reg_no], [contact_detail_id]) VALUES (?,?,?);";
            PreparedStatement supplierPs = conn.getConnection().prepareStatement(supplierQuery);
            supplierPs.setString(1, supplier.getName());
            supplierPs.setString(2, supplier.getCompanyRegNo());
            supplierPs.setInt(3, supplier.getContactId());
            conn.uploadSafe(supplierPs);
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
            conn = new DBConnect();
            String updateContactQuery = "UPDATE contact_details SET phone = ?, email = ?, address = ?, postcode = ?, country = ? WHERE id = '" + supplier.getContactId() + "'";
            PreparedStatement updatePs = conn.getConnection().prepareStatement(updateContactQuery);
            updatePs.setString(1, supplier.getPhone());
            updatePs.setString(2, supplier.getEmail());
            updatePs.setString(3, supplier.getAddress());
            updatePs.setString(4, supplier.getPostcode());
            updatePs.setString(5, supplier.getCountry());
            conn.uploadSafe(updatePs);
            String updateSupplierQuery = "UPDATE suppliers SET name = ?, co_reg_no WHERE id= '" + supplier.getId() + "'";
            PreparedStatement updateSuppPs = conn.getConnection().prepareStatement(updateSupplierQuery);
            updateSuppPs.setString(1, supplier.getName());
            updateSuppPs.setString(2, supplier.getCompanyRegNo());
            conn.uploadSafe(updateSuppPs);
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
            conn = new DBConnect();
            String query = "DELETE [a.*], [b.*] FROM [contact_details] a INNER JOIN [suppliers] b ON [a.id] = [b.contact_detail_id] WHERE [b.id] = '" + supplier.getId() + "' ";
            conn.upload(query);
        } catch (ConnectionException e) {
            throw new ModelSyncException("Could not delete the supplier.", e);
        }

    }
}
