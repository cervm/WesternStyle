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
    private boolean isLoaded;

    public DBProducts() {
        products = new ArrayList<>();
        isLoaded = false;
    }

    void load() throws ModelSyncException {
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
    }

    /**
     * Method to retrieve all products from the database
     *
     * @return all products in the database
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public List<Product> getAll() throws ModelSyncException {
        if (!isLoaded) {
            load();
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

            String productQuery = "INSERT INTO [products] ([name], [cost_price], [sales_price], [rent_price], [country_code], [min_stock], [description], [supplier_id]) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(productQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getCostPrice());
            stmt.setDouble(3, product.getSalesPrice());
            stmt.setDouble(4, product.getRentPrice());
            stmt.setString(5, product.getCountryOrigin());
            stmt.setInt(6, product.getMinStock());
            stmt.setString(7, product.getDescription());
            stmt.setInt(8, product.getSupplierId());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                } else {
                    throw new ModelSyncException("Creating contact details failed. No ID retrieved!");
                }
            }
            stmt.close();
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not create the product.", e);
        } finally {
            products.add(product);
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
    public void delete(Product product) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                    "DELETE FROM products\n" +
                            "WHERE id = ?;"
            );
            stmt.setInt(1, product.getId());
            stmt.execute();
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not delete the product!", e);
        } finally {
            products.removeIf(p -> p.getId() == product.getId());
        }

    }
}
