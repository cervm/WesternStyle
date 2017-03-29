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
