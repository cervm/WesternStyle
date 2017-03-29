package model;

import java.util.ArrayList;

/**
 * Created by jakub on 29/03/2017.
 */
public class Category {
    private int cid;
    private String name;
    private Category parentCategory;
    private ArrayList<Property> properties;

    public Category(int cid, String name, Category parentCategory, ArrayList<Property> properties) {
        this.cid = cid;
        this.name = name;
        this.parentCategory = parentCategory;
        this.properties = properties;
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public Category getParentCategory() {
        return parentCategory;
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