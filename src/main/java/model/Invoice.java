package model;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Invoice Entity
 */
public class Invoice {
    private int id;
    private Date paymentDate;
    private double amount;
    private ArrayList<Integer> orders;

    public Invoice(int id, Date paymentDate, double amount, ArrayList<Integer> orders) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.orders = orders;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        String s = "[INVOICE]: id=" + id + " ," +
                " paymentDate=" + paymentDate.toString() + " ," +
                " amount=" + amount + " , ordersIDs=";
        for (int i : orders) {
            s += i + ",";
        }
        return s;
    }
}
