package model.entity;

/**
 * Created by rajmu on 17.03.28.
 */
public abstract class ContactDetails {
    private int id;
    private String phone, email, address, postcode, city, country;

    protected int getContactId() {
        return id;
    }

    protected String getPhone() {
        return phone;
    }

    protected String getEmail() {
        return email;
    }

    protected String getAddress() {
        return address;
    }

    protected String getPostcode() {
        return postcode;
    }

    protected String getCity() {
        return city;
    }

    protected String getCountry() {
        return country;
    }

    protected ContactDetails(int id, String phone, String email, String address, String postcode, String city, String country) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }
}
