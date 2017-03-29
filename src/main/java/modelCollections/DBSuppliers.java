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
 * Created by rajmu on 17.03.28.
 */
public class DBSuppliers implements IDataAccessObject<Supplier> {

    private List<Supplier> suppliers;
    private DBConnect conn;
    private boolean isLoaded;

    public DBSuppliers() throws ModelSyncException {
        suppliers = new ArrayList<>();
        isLoaded = false;
    }

    public void load() throws ModelSyncException {
        suppliers = new ArrayList<>();
        try {
            conn = new DBConnect();
            String query = "SELECT id, phone, email, address, postcode, city, country, suppliers.id, suppliers.name, suppliers.co_reg_no FROM contact_details INNER JOIN suppliers ON suppliers.contact_detail_id = contact_details.id";
            ResultSet rs = conn.getFromDataBase(query);
            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getInt("id"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("postcode"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getInt("suppliers.id"),
                        rs.getString("suppliers.name"),
                        rs.getString("suppliers.co_reg_no")
                ));
            }
            isLoaded = true;
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load customers.", e);
        }
    }
    @Override
    public List<Supplier> getAll() throws ModelSyncException {
        if(!isLoaded){
            load();
        }
        return suppliers;
    }

    @Override
    public Supplier getById(int id) throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        if (id >= suppliers.size() || id < 0) {
            throw new ModelSyncException("ID is out of range!");
        }
        return suppliers.stream().filter(o -> o.getsId() == id).findFirst().get();
    }

    @Override
    public void create(Supplier... objects) throws ModelSyncException{
        for (int i = 0; i <= objects.length; i++){
            try{
                conn = new DBConnect();
                int supplierContactId = 0;
                String contactQuery = "INSERT INTO contact_details (phone, email, address, postcode, city, country) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement contactPs = conn.getConnection().prepareStatement(contactQuery);
                contactPs.setString(2, objects[i].getPhone());
                contactPs.setString(3, objects[i].getEmail());
                contactPs.setString(4, objects[i].getAddress());
                contactPs.setString(5, objects[i].getPostcode());
                contactPs.setString(6, objects[i].getCountry());
                conn.uploadSafe(contactPs);
                ResultSet rs = conn.getFromDataBase("SELECT id FROM contact_details WHERE phone = '" + objects[i].getPhone() + "' AND email = '" + objects[i].getEmail() +"'");
                while(rs.next()){
                    supplierContactId = rs.getInt("id");
                }
                String supplierQuery = "INSERT INTO suppliers (name, co_reg_no, contact_detail_id) VALUES (?, ?, ?)";
                PreparedStatement supplierPs = conn.getConnection().prepareStatement(supplierQuery);
                supplierPs.setString(1, objects[i].getName());
                supplierPs.setString(2, objects[i].getCompanyRegNo());
                supplierPs.setInt(3, supplierContactId);
                conn.uploadSafe(supplierPs);


            } catch (ConnectionException | SQLException e) {
                throw new ModelSyncException("Could not load customers.", e);
            }
        }

    }

    @Override
    public void update(Supplier... objects) throws ModelSyncException{
        for (int i = 0; i <= objects.length; i++){
            try{
                conn = new DBConnect();
                int contactDetailId = 0;

                ResultSet rs = conn.getFromDataBase("SELECT contact_detail_id FROM suppliers WHERE id='"+objects[i].getsId()+"'");
                while(rs.next()){
                    contactDetailId = rs.getInt("contact_detail_id");
                }
                String updateContactQuery = "UPDATE contact_details SET phone = ?, email = ?, address = ?, postcode = ?, country = ? WHERE id = '"+contactDetailId+"'";
                PreparedStatement updatePs = conn.getConnection().prepareStatement(updateContactQuery);
                updatePs.setString(1, objects[i].getPhone());
                updatePs.setString(2, objects[i].getEmail());
                updatePs.setString(3, objects[i].getAddress());
                updatePs.setString(4, objects[i].getPostcode());
                updatePs.setString(5, objects[i].getCountry());
                conn.uploadSafe(updatePs);
                String updateSupplierQuery = "UPDATE suppliers SET name = ?, co_reg_no WHERE id= '"+objects[i].getsId()+"'";
                PreparedStatement updateSuppPs = conn.getConnection().prepareStatement(updateSupplierQuery);
                updateSuppPs.setString(1, objects[i].getName());
                updateSuppPs.setString(2, objects[i].getCompanyRegNo());
                conn.uploadSafe(updateSuppPs);
            } catch (ConnectionException | SQLException e) {
                throw new ModelSyncException("Could not load customers.", e);
            }

        }

    }

    @Override
    public void delete(Supplier... objects) throws ModelSyncException{
        for (int i = 0; i <= objects.length; i++){
            try {
                conn = new DBConnect();
                String query = "DELETE a.*, b.* FROM contact_details a INNER JOIN suppliers b ON a.id = b.contact_detail_id WHERE b.id = '"+objects[i].getsId()+"' ";
                conn.upload(query);
            } catch (ConnectionException e) {
                throw new ModelSyncException("Could not load customers.", e);
            }
        }

    }
}
