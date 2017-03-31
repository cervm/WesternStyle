package model;

import java.util.ArrayList;

/**
 * Product Entity
 */
public class Product {
    private int id, minStock, supplierId;
    private String name, countryOrigin, description;
    private double costPrice, salesPrice, rentPrice;
    private Category primaryCategory;
    private ArrayList<Category> categories;

    public Product(int minStock, String name, String countryOrigin, String description, double costPrice, double salesPrice, double rentPrice, int supplierId) {
        this.minStock = minStock;
        this.name = name;
        this.countryOrigin = countryOrigin;
        this.description = description;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.rentPrice = rentPrice;
        this.supplierId = supplierId;
    }

    public Product(int id, int minStock, String name, String countryOrigin, String description, double costPrice, double salesPrice, double rentPrice, Category primaryCategory, ArrayList<Category> categories) {
        this.id = id;
        this.minStock = minStock;
        this.name = name;
        this.countryOrigin = countryOrigin;
        this.description = description;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.rentPrice = rentPrice;
        this.primaryCategory = primaryCategory;
        this.categories = categories;
    }

    public Product(int id, int minStock, String name, String countryOrigin, String description, double costPrice, double salesPrice, double rentPrice) {
        this.id = id;
        this.minStock = minStock;
        this.name = name;
        this.countryOrigin = countryOrigin;
        this.description = description;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.rentPrice = rentPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Category getPrimaryCategory() {
        return primaryCategory;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category c) {
        categories.add(c);
    }

    public void removeCategory(Category c) {
        categories.removeIf(o -> o == c);
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public void setPrimaryCategory(Category primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
