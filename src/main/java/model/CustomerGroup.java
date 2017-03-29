package model;

/**
 * Created by rajmu on 17.03.28.
 */
public class CustomerGroup {
    private int gid;
    private String name;
    private double discount;

    public CustomerGroup(int gid, String name, double discount) {
        this.gid = gid;
        this.name = name;
        this.discount = discount;
    }

    public int getGid() {
        return gid;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }
}
