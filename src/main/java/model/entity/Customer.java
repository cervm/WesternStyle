package model.entity;

/**
 * Created by rajmu on 17.03.28.
 */
public class Customer extends ContactDetails {
    private int cid;
    private int groupID;
    private String name;

    public Customer(int id, String phone, String email, String address, String postcode, String city, String country, int id1, String name, int groupID) {
        super(id, phone, email, address, postcode, city, country);
        this.cid = id1;
        this.name = name;
        this.groupID = groupID;
    }

    public int getCid() {
        return cid;
    }

    public int getGroupID() {
        return groupID;
    }

    public String getName() {
        return name;
    }

    @Override
    protected int getContactId() {
        return super.getContactId();
    }

    @Override
    protected String getPhone() {
        return super.getPhone();
    }

    @Override
    protected String getEmail() {
        return super.getEmail();
    }

    @Override
    protected String getAddress() {
        return super.getAddress();
    }

    @Override
    protected String getPostcode() {
        return super.getPostcode();
    }

    @Override
    protected String getCity() {
        return super.getCity();
    }

    @Override
    protected String getCountry() {
        return super.getCountry();
    }
}
