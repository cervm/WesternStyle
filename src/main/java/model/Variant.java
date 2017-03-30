package model;

import java.util.ArrayList;

/**
 * Variant Entity
 */
public class Variant {
    private int vid;
    private int quantity;
    private ArrayList<Property> properties;
    private Product product;

    public Variant(int vid, Product product, int quantity, ArrayList<Property> properties) {
        this.vid = vid;
        this.quantity = quantity;
        this.properties = properties;
        this.product = product;
    }

    public int getVid() {
        return vid;
    }

    public int getQuantity() {
        return quantity;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property p) {
        properties.add(p);
    }

    public void removeProperty(Property p) {
        properties.removeIf(o -> o == p);
    }

    public Product getProduct() {
        return product;
    }
}
