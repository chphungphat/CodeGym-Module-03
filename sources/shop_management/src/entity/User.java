package entity;

import lombok.Builder;

@Builder
public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Address address;

    public User(int id, String name, String email, String phone, String password, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
    }

    public User() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%d %s\n" +
                "Email: %s\n" +
                "Phone numbers: %s\n" +
                "Address: %s", id, name, email, phone, address.toString());
    }
}
