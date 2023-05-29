package builder;

import entity.Address;

public interface IAddressBuilder {
    IAddressBuilder number(String number);
    IAddressBuilder street(String street);
    IAddressBuilder ward(String ward);
    IAddressBuilder district(String district);
    IAddressBuilder province(String province);
    IAddressBuilder country(String country);
    Address build();
}
