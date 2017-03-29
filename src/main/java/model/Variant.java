package model;

/**
 * Created by jakub on 29/03/2017.
 */
public class Variant {
    private int vid;
    private int quantity;

    public Variant(int vid, Product product, int quantity) {
        this.vid = vid;
        this.quantity = quantity;
    }

    public int getVid() {
        return vid;
    }

    public int getQuantity() {
        return quantity;
    }
}
