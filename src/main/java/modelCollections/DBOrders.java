package modelCollections;

import model.Order;
import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Orders Data Access Object
 */
public class DBOrders implements IDataAccessObject<Order> {
    private List<Order> orders;
    private DBConnect dbConnect;
    private boolean isLoaded;

    /**
     * Initialize empty lists for collections
     */
    public DBOrders() throws ModelSyncException {
        orders = new ArrayList<>();
        isLoaded = false;
    }

    /**
     * Loads all orders from the database without products and variants
     *
     * @throws ModelSyncException connection or SQL exception
     */
    public void load() throws ModelSyncException {
        orders = new ArrayList<>();
        try {
            dbConnect = new DBConnect();
            String query = "SELECT\n" +
                    "  [id],\n" +
                    "  [order_date],\n" +
                    "  [amount],\n" +
                    "  [delivery_status],\n" +
                    "  [invoice_id],\n" +
                    "  [customer_id],\n" +
                    "  [delivery_date] \n" +
                    "FROM [orders]";

            ResultSet ordersRS = dbConnect.getFromDataBase(query);

            while (ordersRS.next()) {
                orders.add(new Order(
                        ordersRS.getInt("id"),
                        ordersRS.getDate("order_date"),
                        ordersRS.getDouble("amount"),
                        ordersRS.getBoolean("delivery_status"),
                        ordersRS.getInt("invoice_id"),
                        ordersRS.getInt("customer_id"),
                        ordersRS.getDate("delivery_date")
                ));
            }
            isLoaded = true;
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load orders.", e);
        }
    }

    /**
     * Method to retrieve all orders from the database
     *
     * @return list of orders
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public List<Order> getAll() throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        return orders;
    }

    /**
     * Method to get an order from the database by id
     *
     * @param id id of the order
     * @return order matching the id
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public Order getById(int id) throws ModelSyncException {
        if (!isLoaded) {
            load();
        }

        return orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    /**
     * Method to persist an order in the database
     *
     * @param order order to be persisted
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public Order create(Order order) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            String orderQuery = "INSERT INTO [orders] ([order_date], [amount], [delivery_status], [invoice_id], [customer_id],[delivery_date]) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(orderQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, order.getOrderDate());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setBoolean(3, order.getDeliveryStatus());
            preparedStatement.setInt(4, order.getInvoiceId());
            preparedStatement.setInt(5, order.getCustomerId());
            preparedStatement.setDate(6, order.getDeliveryDate());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                } else {
                    throw new ModelSyncException("Creating order failed. No ID retrieved!");
                }
            }
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not create new order!", e);
        } finally {
            orders.add(order);
        }

        return order;
    }

    /**
     * Method to update an order in the database
     *
     * @param order order to be updated
     * @throws ModelSyncException connection or SQL exception
     */
    @Override
    public void update(Order order) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();

            String updateOrderQry = "UPDATE [orders] SET [order_date] = ?, [amount] = ?, [delivery_status] = ?, [invoice_id] = ?, [customer_id] = ?, [delivery_date] = ? WHERE [id]=?;";

            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(updateOrderQry);
            preparedStatement.setDate(1, order.getOrderDate());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setBoolean(3, order.getDeliveryStatus());
            preparedStatement.setInt(4, order.getInvoiceId());
            preparedStatement.setInt(5, order.getCustomerId());
            preparedStatement.setDate(6, order.getDeliveryDate());
            preparedStatement.setInt(7, order.getId());
            dbConnect.uploadSafe(preparedStatement);
        } catch (SQLException | ConnectionException e) {
            throw new ModelSyncException("WARNING! Could not update the order of id [" + order.getId() + "]!", e);
        }
    }

    /**
     * Method to delete an order from the database
     *
     * @param order order to be deleted
     */
    @Override
    public void delete(Order order) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement stmt = dbConnect.getConnection().prepareStatement(
                    "DELETE FROM [orders]\n" +
                            "WHERE [id] = ?;");
            stmt.setInt(1, order.getId());
            stmt.execute();
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Couldn't delete order of id=" + order.getId(), e);
        } finally {
            orders.removeIf(p -> p.getId() == order.getId());
        }

    }
}
