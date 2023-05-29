package builder;

import entity.Address;
import entity.Customer;

import java.time.LocalDate;
import java.util.List;

public interface ICustomerBuilder {
    ICustomerBuilder name(String name);
    ICustomerBuilder phone(String phone);
    ICustomerBuilder password(String password);
    ICustomerBuilder birthday(LocalDate birthday);
    ICustomerBuilder address(Address address);
    ICustomerBuilder email(String email);
    ICustomerBuilder wallet(long wallet);
    Customer build();
}
