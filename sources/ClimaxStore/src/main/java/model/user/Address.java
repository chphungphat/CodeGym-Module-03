package model.user;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    private int address_id;
    private int user_id;
    private String houseNumber;
    private String street;
    private String ward;
    private String district;
    private String province;
    private String country;
}
