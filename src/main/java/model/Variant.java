package model;

import java.util.ArrayList;

/**
 * Created by jakub on 29/03/2017.
 */
public class Variant {
    private int vid;
    private int quantity;
    private ArrayList<Property> properties;

    public Variant(int vid, Product product, int quantity, ArrayList<Property> properties) {
        this.vid = vid;
        this.quantity = quantity;
        this.properties = properties;
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
}
