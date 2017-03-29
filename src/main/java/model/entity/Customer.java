package model.entity;

/**
 * Customer Entity
 */
public class Customer extends ContactDetails {
    private int groupId;

    public Customer(int id, String name, String phone, String email, String address, String postcode, String city, String country_code, int groupId) {
        super(id, name, phone, email, address, postcode, city, country_code);
        setGroupID(groupId);
    }

    public Customer(String name, String phone, String email, String address, String postcode, String city, String country, int groupId) {
        super(name, phone, email, address, postcode, city, country);
        this.groupId = groupId;
    }

    public int getGroupID() {
        return groupId;
    }

    public void setGroupID(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getContactId() {
        return super.getContactId();
    }

    @Override
    public void setContactId(int id) {
        super.setContactId(id);
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    @Override
    public String getPostcode() {
        return super.getPostcode();
    }

    @Override
    public void setPostcode(String postcode) {
        super.setPostcode(postcode);
    }

    @Override
    public String getCity() {
        return super.getCity();
    }

    @Override
    public void setCity(String city) {
        super.setCity(city);
    }

    @Override
    public String getCountry() {
        return super.getCountry();
    }

    @Override
    public void setCountry(String country) {
        super.setCountry(country);
    }
}
