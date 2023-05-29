package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class User {
    private static int count = 0;

    protected int id;
    protected String name;
    protected String phone;
    protected String password;
    protected LocalDate birthday;
    protected Address address;
    protected String email;

    public User() {}

    public User(String name, String phone, String password, LocalDate birthday, Address address, String email) {
        this.id = count++;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        User.count = count;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return (id + " " + name + "\n"
                + "Phone: " + phone + "\n"
                + "Email: " + email + "\n"
                + "Birthday: " + birthday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n"
                + "Address: " + address.toString());
    }
}
