package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ondřej Soukup on 28.03.2017.
 */
public class Order {
    private int id, invoiceId;
    private Date orderDate, deliveryDate;
    private double amount;
    private boolean deliveryStatus;
    private ArrayList<BasketItem> items;

    public Order(int id, Date orderDate, double amount, boolean deliveryStatus, Date deliveryDate, int invoiceId, ArrayList<BasketItem> items) {
        this.id = id;
        this.orderDate = orderDate;
        this.amount = amount;
        this.deliveryStatus = deliveryStatus;
        this.deliveryDate = deliveryDate;
        this.invoiceId = invoiceId;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public double getAmount() {
        return amount;
    }

    public boolean getDeliveryStatus() {
        return deliveryStatus;
    }

    public void addItem(BasketItem item) {
        items.add(item);
    }

    public void removeItem(BasketItem item) {
        items.removeIf(o -> o == item);
    }
}
