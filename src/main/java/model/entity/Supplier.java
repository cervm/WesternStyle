package model.entity;

/**
 * Supplier Entity
 */
public class Supplier extends ContactDetails {
    private String companyRegNo;

    public Supplier(int id, String name, String phone, String email, String address, String postcode, String city, String country, String companyRegNo) {
        super(id, name, phone, email, address, postcode, city, country);
        setCompanyRegNo(companyRegNo);
    }

    public Supplier(String name, String phone, String email, String address, String postcode, String city, String country, String companyRegNo) {
        super(name, phone, email, address, postcode, city, country);
        this.companyRegNo = companyRegNo;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    private void setCompanyRegNo(String companyRegNo) {
        this.companyRegNo = companyRegNo;
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
