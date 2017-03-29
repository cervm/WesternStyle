package model;

import java.util.ArrayList;

/**
 * Created by Ondřej Soukup on 28.03.2017.
 */
public class Product {
    private int id, minStock;
    private String name, countryOrigin, description;
    private double costPrice, salesPrice, rentPrice;
    private Category primatyCategory;
    private ArrayList<Category> categories;

    public Product(int id, int minStock, String name, String countryOrigin, String description, double costPrice, double salesPrice, double rentPrice, Category primatyCategory, ArrayList<Category> categories) {
        this.id = id;
        this.minStock = minStock;
        this.name = name;
        this.countryOrigin = countryOrigin;
        this.description = description;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.rentPrice = rentPrice;
        this.primatyCategory = primatyCategory;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public int getMinStock() {
        return minStock;
    }

    public String getName() {
        return name;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public String getDescription() {
        return description;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public Category getPrimatyCategory() {
        return primatyCategory;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
