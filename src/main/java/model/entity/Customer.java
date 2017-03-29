package model.entity;

/**
 * Created by rajmu on 17.03.28.
 */
public class Customer extends ContactDetails {
    private int groupId;

    public Customer(int id, String name, String phone, String email, String address, String postcode, String city, String country_code, int groupId) {
        super(id, name, phone, email, address, postcode, city, country_code);
        setGroupID(groupId);
    }

    public int getGroupID() {
        return groupId;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public void setGroupID(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
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
