package model.entity;

/**
 * Created by rajmu on 17.03.28.
 */
public abstract class ContactDetails {
    private int id;
    private String phone, email, address, postcode, city, country;

    public int getContactId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setContactId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ContactDetails(int id, String phone, String email, String address, String postcode, String city, String country) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public ContactDetails(String phone, String email, String address, String postcode, String city, String country) {
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }
}
