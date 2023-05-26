package entity;

import lombok.Builder;

@Builder
public class Address {
    private int address_id;
    private String houseNumber;
    private String street;
    private String ward;
    private String district;
    private String province;
    private String country;

    public Address(int address_id, String houseNumber, String street, String ward, String district, String province, String country) {
        this.address_id = address_id;
        this.houseNumber = houseNumber;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.province = province;
        this.country = country;
    }

    public Address() {}

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", houseNumber, street, ward, district, province, country);
    }
}
