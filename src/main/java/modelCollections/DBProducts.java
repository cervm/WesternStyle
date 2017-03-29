package modelCollections;

import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.Product;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Products Data Access Object
 */
public class DBProducts implements IDataAccessObject<Product> {
    private List<Product> products;
    private DBConnect dbConnect;

    public DBProducts() throws ModelSyncException {
        products = new ArrayList<>();
        try {
            dbConnect = new DBConnect();
            String query = "SELECT id FROM products";
            ResultSet rs = dbConnect.getFromDataBase(query);
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getInt("min_stock"),
                        rs.getString("name"),
                        rs.getString("country_code"),
                        rs.getString("description"),
                        rs.getDouble("cost_price"),
                        rs.getDouble("sales_price"),
                        rs.getDouble("rent_price")
                ));
            }

        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load customers.", e);
        }
    }

    @Override
    public List<Product> getAll() {
        return null; //TODO: to be implemented
    }

    @Override
    public Product getById(int id) {
        return null; //TODO: to be implemented
    }

    @Override
    public void create(Product product) {
        try {
            dbConnect = new DBConnect();
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(
                    "INSERT INTO [products] ([name], [cost_price], [sales_price], [rent_price], [country_code], [min_stock], [description]) VALUES (?, ?, ?, ?, ?, ?, ?); ");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getCostPrice());
            preparedStatement.setDouble(3, product.getSalesPrice());
            preparedStatement.setDouble(4, product.getRentPrice());
            preparedStatement.setString(5, product.getCountryOrigin());
            preparedStatement.setInt(6, product.getMinStock());
            preparedStatement.setString(7, product.getDescription());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product object) {
        //TODO: to be implemented
    }

    @Override
    public void delete(Product object) {
        //TODO: to be implemented
    }
}
