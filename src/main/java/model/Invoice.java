package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Invoice Entity
 */
public class Invoice {
    private int id, numOfOrders;
    private Date paymentDate;
    private double amount;
    private ArrayList<Integer> orders;

    public Invoice(Date paymentDate, double amount) {
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Invoice(int id, Date paymentDate, double amount, ArrayList<Integer> orders) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.orders = orders;
        numOfOrders = orders.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void addOrder(int orderId) {
        orders.add(orderId);
    }

    public void deleteOrder(int orderId) {
        orders.removeIf(o -> o == orderId);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<Integer> getOrders() {
        return orders;
    }

    public int getNumOfOrders() {
        numOfOrders = orders.size();
        return numOfOrders;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[INVOICE]: id=" + id + " ," +
                " paymentDate=" + paymentDate.toString() + " ," +
                " amount=" + amount + " , ordersIDs=");
        for (int i : orders) {
            s.append(i).append(",");
        }
        return s.toString();
    }
}
