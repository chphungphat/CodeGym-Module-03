package entity;

import lombok.Builder;

@Builder
public class Address {
    private String houseNumber;
    private String street;
    private String ward;
    private String district;
    private String province;
    private String country;

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", houseNumber, street, ward, district, province, country);
    }
}
