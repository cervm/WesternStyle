package model.entity;

/**
 * Created by rajmu on 17.03.28.
 */
public class Supplier extends ContactDetails {
    int sId;
    String name;
    String companyRegNo;

    public Supplier(int id, String phone, String email, String address, String postcode, String city, String country, int sId, String name, String companyRegNo) {
        super(id, phone, email, address, postcode, city, country);
        this.sId = sId;
        this.name = name;
        this.companyRegNo = companyRegNo;
    }

    public int getsId() {
        return sId;
    }

    public String getName() {
        return name;
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyRegNo(String companyRegNo) {
        this.companyRegNo = companyRegNo;
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
