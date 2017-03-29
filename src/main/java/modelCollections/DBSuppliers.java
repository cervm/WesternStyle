package modelCollections;

import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.entity.Supplier;
import model.exception.ConnectionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by rajmu on 17.03.28.
 */
public class DBSuppliers implements IDataAccessObject<Supplier> {

    private List<Supplier> suppliers;
    private DBConnect conn;

    public Suppliers(){

    }
    @Override
    public List<Supplier> getAll() {
        try{
            conn = new DBConnect();
            ResultSet suppliersRs = conn.getFromDataBase("SELECT id, phone, email, address, postcode, city, country, suppliers.id, suppliers.name, suppliers.co_reg_no FROM contact_details INNER JOIN suppliers ON suppliers.contact_detail_id = contact_details.id");
            while(suppliersRs.next()){
                Supplier s = new Supplier(suppliersRs.getInt("id"),
                        suppliersRs.getString("phone"),
                        suppliersRs.getString("email"),
                        suppliersRs.getString("address"),
                        suppliersRs.getString("postcode"),
                        suppliersRs.getString("city"),
                        suppliersRs.getString("country"),
                        suppliersRs.getInt("suppliers.id"),
                        suppliersRs.getString("suppliers.name"),
                        suppliersRs.getString("suppliers.co_reg_no"));
                suppliers.add(s);
            }
            return suppliers;
        }
        catch(ConnectionException e){
            System.out.println("Suppliers collection can't acces the db.");
            return null;
        }
        catch(SQLException e){
            System.out.println("Suppliers collection can't process the data.");
            return null;
        }
    }

    @Override
    public Supplier getById(int id) {
        try{
            conn = new DBConnect();
            Supplier s = null;
            ResultSet suppliersRs = conn.getFromDataBase("SELECT id, phone, email, address, postcode, city, country, suppliers.id, suppliers.name, suppliers.co_reg_no FROM contact_details INNER JOIN suppliers ON suppliers.contact_detail_id = contact_details.id WHERE suppliers.id = '" + id +"'");
            while(suppliersRs.next()){
                s = new Supplier(suppliersRs.getInt("id"),
                        suppliersRs.getString("phone"),
                        suppliersRs.getString("email"),
                        suppliersRs.getString("address"),
                        suppliersRs.getString("postcode"),
                        suppliersRs.getString("city"),
                        suppliersRs.getString("country"),
                        suppliersRs.getInt("suppliers.id"),
                        suppliersRs.getString("suppliers.name"),
                        suppliersRs.getString("suppliers.co_reg_no"));
            }
            return s;
        }
        catch(ConnectionException e){
            System.out.println("Suppliers collection can't acces the db.");
            return null;
        }
        catch(SQLException e){
            System.out.println("Suppliers collection can't process the data.");
            return null;
        }
    }

    @Override
    public void create(Supplier object) {
        try{
            conn = new DBConnect();
            Supplier s = object;
        }
        catch(ConnectionException e){
            System.out.println("Suppliers collection can't acces the db.");

        }
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
