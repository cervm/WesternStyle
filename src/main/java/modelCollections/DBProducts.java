package modelCollections;

import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.Product;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Products Data Access Object
 */
public class DBProducts implements IDataAccessObject<Product> {
    private DBConnect dbConnect;

    @Override
    public List<Product> getAll() throws ModelSyncException {
        List<Product> products = new LinkedList<>();
        try {
            dbConnect = new DBConnect();
            ResultSet rs = dbConnect.getFromDataBase("SELECT * FROM products");
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
            throw new ModelSyncException("Could not load products.", e);
        }
        return products;
    }

    public List<Product> getByCategory() throws ModelSyncException {
        List<Product> products = new LinkedList<>();
        try {
            dbConnect = new DBConnect();
            Statement statement = dbConnect.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT *\n" +
                            "FROM products AS p\n" +
                            "JOIN product_categories AS c ON c.product_id = p.id\n" +
                            "WHERE c.id = ?;");
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
            throw new ModelSyncException("Could not load products.", e);
        }
        return products;
    }

    @Override
    public Product getById(int id) throws ModelSyncException {
        Product product;
        try {
            dbConnect = new DBConnect();
            ResultSet rs = dbConnect.getFromDataBase("SELECT * FROM products WHERE id = " + id);
            rs.next();
            product = new Product(
                    rs.getInt("id"),
                    rs.getInt("min_stock"),
                    rs.getString("name"),
                    rs.getString("country_code"),
                    rs.getString("description"),
                    rs.getDouble("cost_price"),
                    rs.getDouble("sales_price"),
                    rs.getDouble("rent_price")
            );
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load products.", e);
        }
        return product;
    }

    @Override
    public void create(Product... objects) {
        for (int i = 0; i <= objects.length; i++){
            try {
                dbConnect = new DBConnect();
                PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(
                        "INSERT INTO [products] ([name], [cost_price], [sales_price], [rent_price], [country_code], [min_stock], [description]) VALUES (?, ?, ?, ?, ?, ?, ?); ");
                preparedStatement.setString(1, objects[i].getName());
                preparedStatement.setDouble(2, objects[i].getCostPrice());
                preparedStatement.setDouble(3, objects[i].getSalesPrice());
                preparedStatement.setDouble(4, objects[i].getRentPrice());
                preparedStatement.setString(5, objects[i].getCountryOrigin());
                preparedStatement.setInt(6, objects[i].getMinStock());
                preparedStatement.setString(7, objects[i].getDescription());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (ConnectionException | SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(Product... objects) {
        for (int i = 0; i <= objects.length; i++){

        }
        //TODO: to be implemented
    }

    @Override
    public void delete(Product... objects) {
        for (int i = 0; i <= objects.length; i++){

        }
        //TODO: to be implemented
    }
}
