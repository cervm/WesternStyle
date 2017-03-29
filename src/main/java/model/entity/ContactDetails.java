package model.entity;

/**
 * Created by rajmu on 17.03.28.
 */
public abstract class ContactDetails {
    private int id;
    private String name, phone, email, address, postcode, city, country;

    ContactDetails(int id, String name, String phone, String email, String address, String postcode, String city, String country) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public ContactDetails(String name, String phone, String email, String address, String postcode, String city, String country) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public int getContactId() {
        return id;
    }

    public void setContactId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
