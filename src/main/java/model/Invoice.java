package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Invoice Entity
 */
public class Invoice {
    private int id, orderId;
    private Date paymentDate;
    double amount;
    private ArrayList<Integer> orders;

    public Invoice(int id, Date paymentDate, double amount, int orderId) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        orders = new ArrayList<>();
        orders.add(orderId);
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
        orders.removeIf(o -> (int) o == orderId);
    }

}
