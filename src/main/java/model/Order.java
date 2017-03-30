package model;

import model.entity.Customer;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Order Entity
 */
public class Order {
    private int id, invoiceId;
    private Date orderDate, deliveryDate;
    private double amount;
    private boolean deliveryStatus;
    private ArrayList<BasketItem> items;
    private int customerId;

    public Order(int id, int invoiceId, Date orderDate, Date deliveryDate, double amount, boolean deliveryStatus, ArrayList<BasketItem> items, int customerId) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.amount = amount;
        this.deliveryStatus = deliveryStatus;
        this.items = items;
        this.customerId = customerId;
    }

    public Order(int id, int invoiceId, Date orderDate, Date deliveryDate, double amount, boolean deliveryStatus) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.amount = amount;
        this.deliveryStatus = deliveryStatus;
    }

    public Order(int invoiceId, Date orderDate, Date deliveryDate, double amount, boolean deliveryStatus, int customerId) {
        this.invoiceId = invoiceId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.amount = amount;
        this.deliveryStatus = deliveryStatus;
        this.customerId = customerId;
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

    public ArrayList<BasketItem> getItems() {
        return items;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void addItem(BasketItem item) {
        items.add(item);
    }

    public void removeItem(BasketItem item) {
        items.removeIf(o -> o == item);
    }
}
