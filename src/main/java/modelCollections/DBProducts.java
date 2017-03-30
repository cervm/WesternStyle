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
import java.util.LinkedList;
import java.util.List;

/**
 * Products Data Access Object
 */
public class DBProducts implements IDataAccessObject<Product> {
    private LinkedList<Product> products;
    private DBConnect dbConnect;

    /**
     * Method to retrieve all products from the database
     *
     * @return all products in the database
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public List<Product> getAll() throws ModelSyncException {
        products = new LinkedList<>();
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
        List<Product> products = new LinkedList<>();
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
     * @return product by id
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
     * @param objects products to be persisted
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public void create(Product... objects) throws ModelSyncException {
        for (int i = 0; i <= objects.length; i++) {
            try {
                dbConnect = new DBConnect();
                PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(
                        "INSERT INTO [products] ([name], [cost_price], [sales_price], [rent_price], [country_code], [min_stock], [description])\n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?);"
                );
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
                throw new ModelSyncException("Could not create the product.", e);
            }
        }

    }

    /**
     * Method to update a product in the database
     *
     * @param objects products to be updated
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public void update(Product... objects) throws ModelSyncException {
        for (Product object : objects) {
            try {
                dbConnect = new DBConnect();
                PreparedStatement ps = dbConnect.getConnection().prepareStatement(
                        "UPDATE [products]\n" +
                                "SET [name]      = ?, [cost_price] = ?, [sales_price] = ?, [rent_price] = ?, [country_code] = ?, [min_stock] = ?,\n" +
                                "  [description] = ?, [supplier_id] = ?;"
                );
                ps.setString(1, object.getName());
                ps.setDouble(2, object.getCostPrice());
                ps.setDouble(3, object.getSalesPrice());
                ps.setDouble(4, object.getRentPrice());
                ps.setString(5, object.getCountryOrigin());
                ps.setInt(6, object.getMinStock());
                ps.setString(7, object.getDescription());
                ps.setInt(8, 1);
            } catch (ConnectionException | SQLException e) {
                throw new ModelSyncException("WARNING! Could not update the product of id [" + object.getId() + "]!", e);
            } finally {
                products.removeIf(p -> p.getId() == object.getId());
                products.add(object);
            }
        }
    }

    /**
     * Method to delete a product from the database
     *
     * @param objects products to be deleted
     */
    @Override
    public void delete(Product... objects) {
        for (Product object : objects) {
            try {
                dbConnect = new DBConnect();
                PreparedStatement ps = dbConnect.getConnection().prepareStatement(
                        "DELETE FROM products\n" +
                                "WHERE id = ?;"
                );
                ps.setInt(1, object.getId());
            } catch (ConnectionException | SQLException e) {
                e.printStackTrace();
            } finally {
                products.removeIf(p -> p.getId() == object.getId());
            }

        }
    }
}
