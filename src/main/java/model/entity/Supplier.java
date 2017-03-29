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
