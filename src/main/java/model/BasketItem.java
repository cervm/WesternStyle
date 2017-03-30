package model;

/**
 * Basket Item Entity
 */
public class BasketItem {
    private Product product;
    private Variant variant;
    private int quantity;

    public BasketItem(Product product, Variant variant, int quantity) {
        this.product = product;
        this.variant = variant;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Variant getVariant() {
        return variant;
    }

    public int getQuantity() {
        return quantity;
    }
}
