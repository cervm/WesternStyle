package model;

import java.util.Date;

/**
 * Created by Ondřej Soukup on 28.03.2017.
 */
public class Order {
    private int id, invoiceId;
    private Date orderDate, deliveryDate;
    private double amount;
    private boolean deliveryStatus;

    public Order(int id, Date orderDate, double amount, boolean deliveryStatus, Date deliveryDate, int invoiceId){
        this.id = id;
        this.orderDate = orderDate;
        this.amount = amount;
        this.deliveryStatus = deliveryStatus;
        this.deliveryDate = deliveryDate;
        this.invoiceId = invoiceId;
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
}
