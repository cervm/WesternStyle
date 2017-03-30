package modelCollections;

import model.BasketItem;
import model.connection.DBConnect;
import model.connection.IDataAccessObject;
import model.Order;
import model.exception.ConnectionException;
import model.exception.ModelSyncException;

import java.sql.Date;
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

    public DBOrders() throws ModelSyncException {
        orders = new ArrayList<>();
        isLoaded = false;
    }

    private void load() throws ModelSyncException {
        //doesn't instantiate products, variants or the customer at this point
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
                        ordersRS.getInt("invoice_id"),
                        ordersRS.getDate("order_date"),
                        ordersRS.getDate("delivery_date"),
                        ordersRS.getDouble("amount"),
                        ordersRS.getBoolean("delivery_status")
                ));
            }
            isLoaded = true;
        } catch (ConnectionException | SQLException e) {
            throw new ModelSyncException("Could not load orders.", e);
        }
    }

    @Override
    public List<Order> getAll() throws ModelSyncException {
        if (!isLoaded) {
            load();
        }
        return orders;
    }

    @Override
    public Order getById(int id) throws ModelSyncException {
        if (!isLoaded) {
            load();
        }

        /*if (id >= orders.size() || id < 0) {
            throw new ModelSyncException("ID is out of range!");
        }*/
        return orders.stream().filter(o -> o.getId() == id).findFirst().get();

    }

    @Override
    public Order create(Order order) {
        //creates the row in the orders table
        try {
            dbConnect = new DBConnect();
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(
                    "INSERT INTO [orders] ([order_date], [amount], [delivery_status], [invoice_id], [customer_id],[delivery_date]) VALUES (?, ?, ?, ?, ?, ?);"
            );
            preparedStatement.setDate(1, (Date) order.getOrderDate());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setBoolean(3, order.getDeliveryStatus());
            preparedStatement.setInt(4, order.getInvoiceId());
            preparedStatement.setInt(5, order.getCustomer().getId());
            preparedStatement.setDate(6, (Date) order.getDeliveryDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        }

        //iterates through all of the basketItems of the order and adds them to the order_items table
        for (BasketItem item : order.getItems()) {
            try {
                dbConnect = new DBConnect();
                PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(
                        "INSERT INTO [order_items] ([order_id], [variant_id], [quantity]) VALUES (?, ?, ?);"
                );
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, item.getVariant().getVid());
                preparedStatement.setInt(3, item.getQuantity());
            } catch (ConnectionException | SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }

    @Override
    public void update(Order order) throws ModelSyncException {
        try {
            dbConnect = new DBConnect();

            String updateSupplierQuery = "UPDATE [orders] SET [order_date] = ?, [amount] = ?, [delivery_status] = ?, [invoice_id] = ?, [customer_id] = ?, [delivery_date] = ? WHERE [id]= '" + order.getId() + "'";

            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(updateSupplierQuery);
            preparedStatement.setDate(1, (Date) order.getOrderDate());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setBoolean(3, order.getDeliveryStatus());
            preparedStatement.setInt(4, order.getInvoiceId());
            preparedStatement.setInt(5, order.getCustomer().getId());
            preparedStatement.setDate(6, (Date) order.getDeliveryDate());
            dbConnect.uploadSafe(preparedStatement);
        } catch (SQLException | ConnectionException e) {
            throw new ModelSyncException("WARNING! Could not update the order of id [" + order.getId() + "]!", e);
        }
    }

    @Override
    public void delete(Order order) {
        try {
            dbConnect = new DBConnect();
            PreparedStatement ps = dbConnect.getConnection().prepareStatement(
                    "DELETE FROM [orders]\n" +
                            "WHERE [id] = ?;");
            ps.setInt(1, order.getId());
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        } finally {
            orders.removeIf(p -> p.getId() == order.getId());
        }

        //TODO: Discuss, whether the update and delete functions are actually relevant for orders
    }
}
