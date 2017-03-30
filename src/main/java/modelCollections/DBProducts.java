package modelCollections;

import model.Category;
import model.Product;
import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Products Data Access Object
 */
public class DBProducts implements IDataAccessObject<Product> {
    private ArrayList<Product> products;
    private DBConnect dbConnect;

    /**
     * Method to retrieve all products from the database
     *
     * @return all products in the database
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public List<Product> getAll() throws ModelSyncException {
        products = new ArrayList<>();
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

    /**
     * Method to retrieve all products from the specified category
     *
     * @param category category to search in
     * @return all products in the category
     * @throws ModelSyncException connection or SQL exception
     */
    public List<Product> getByCategory(Category category) throws ModelSyncException {
        List<Product> products = new ArrayList<>();
        try {
            dbConnect = new DBConnect();
            Statement statement = dbConnect.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT *\n" +
                            "FROM products AS p\n" +
                            "JOIN product_categories AS c ON c.product_id = p.id\n" +
                            "WHERE c.id = " + category.getCid() + ";");
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

    /**
     * Method to get a product by id
     *
     * @param id id of the product
     * @return product with the id
     * @throws ModelSyncException connection or SQL exception
     */
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

    /**
     * Method to persist a product in the database
     *
     * @param product product to be persisted
     * @return product with assigned id from the database
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public Product create(Product product) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(
                    "INSERT INTO [products] ([name], [cost_price], [sales_price], [rent_price], [country_code], [min_stock], [description])\n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?);"
            );
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
            throw new ModelSyncException("Could not create the product.", e);
        }
        return product;
    }

    /**
     * Method to update a product in the database
     *
     * @param product product to be updated
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public void update(Product product) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                    "UPDATE [products]\n" +
                            "SET [name]      = ?, [cost_price] = ?, [sales_price] = ?, [rent_price] = ?, [country_code] = ?, [min_stock] = ?,\n" +
                            "  [description] = ?, [supplier_id] = ?;"
            );
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getCostPrice());
            stmt.setDouble(3, product.getSalesPrice());
            stmt.setDouble(4, product.getRentPrice());
            stmt.setString(5, product.getCountryOrigin());
            stmt.setInt(6, product.getMinStock());
            stmt.setString(7, product.getDescription());
            stmt.setInt(8, 1);
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("WARNING! Could not update the product of id [" + product.getId() + "]!", e);
        } finally {
            products.removeIf(p -> p.getId() == product.getId());
            products.add(product);
        }
    }

    /**
     * Method to delete a product from the database
     *
     * @param product product to be deleted
     */
    @Override
    public void delete(Product product) {
        try {
            dbConnect = new DBConnect();
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                    "DELETE FROM products\n" +
                            "WHERE id = ?;"
            );
            stmt.setInt(1, product.getId());
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        } finally {
            products.removeIf(p -> p.getId() == product.getId());
        }

    }
}
