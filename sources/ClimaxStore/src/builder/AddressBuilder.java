package builder;

import entity.Address;

public class AddressBuilder implements IAddressBuilder{
    private String number;
    private String street;
    private String ward;
    private String district;
    private String province;
    private String country;

    private static final AddressBuilder addressBuilder = new AddressBuilder();

    private AddressBuilder() {}

    public static AddressBuilder getInstance() {
        return addressBuilder;
    }

    @Override
    public IAddressBuilder number(String number) {
        this.number = number;
        return this;
    }

    @Override
    public IAddressBuilder street(String street) {
        this.street = street;
        return this;
    }

    @Override
    public IAddressBuilder ward(String ward) {
        this.ward = ward;
        return this;
    }

    @Override
    public IAddressBuilder district(String district) {
        this.district = district;
        return this;
    }

    @Override
    public IAddressBuilder province(String province) {
        this.province = province;
        return this;
    }

    @Override
    public IAddressBuilder country(String country) {
        this.country = country;
        return this;
    }

    @Override
    public Address build() {
        return new Address(number, street, ward, district, province, country);
    }
}
