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

    public Customer(String phone, String email, String address, String postcode, String city, String country, int groupID, String name) {
        super(phone, email, address, postcode, city, country);
        this.groupID = groupID;
        this.name = name;
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

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setContactId(int id) {
        super.setContactId(id);
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    @Override
    public void setPostcode(String postcode) {
        super.setPostcode(postcode);
    }

    @Override
    public void setCity(String city) {
        super.setCity(city);
    }

    @Override
    public void setCountry(String country) {
        super.setCountry(country);
    }

    @Override
    public int getContactId() {
        return super.getContactId();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public String getPostcode() {
        return super.getPostcode();
    }

    @Override
    public String getCity() {
        return super.getCity();
    }

    @Override
    public String getCountry() {
        return super.getCountry();
    }
}
