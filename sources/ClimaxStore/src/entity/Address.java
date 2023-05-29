package entity;

public class Address {
    private String number;
    private String street;
    private String ward;
    private String district;
    private String province;
    private String country;

    public Address() {}

    public Address(String number, String street, String ward, String district, String province, String country) {
        this.number = number;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.province = province;
        this.country = country;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        return String.format("%s %s street, ward %s, district %s, %s, %s", number, street, ward, district, province, country);
    }
}
