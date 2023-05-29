package builder;

import entity.Address;
import entity.Customer;

import java.time.LocalDate;
import java.util.List;

public class CustomerBuilder implements ICustomerBuilder{
    protected String name;
    protected String phone;
    protected String password;
    protected LocalDate birthday;
    protected Address address;
    protected String email;
    protected long wallet;

    private static final CustomerBuilder customerBuilder = new CustomerBuilder();

    private CustomerBuilder() {}

    public static CustomerBuilder getInstance() {
        return customerBuilder;
    }

    @Override
    public ICustomerBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ICustomerBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public ICustomerBuilder password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public ICustomerBuilder birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public ICustomerBuilder address(Address address) {
        this.address = address;
        return this;
    }

    @Override
    public ICustomerBuilder email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public ICustomerBuilder wallet(long wallet) {
        this.wallet = wallet;
        return this;
    }

    @Override
    public Customer build() {
        return new Customer(name, phone, password, birthday, address, email, wallet);
    }
}
