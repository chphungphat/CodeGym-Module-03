import entity.Address;
import entity.User;

import javax.jws.soap.SOAPBinding;

public class Main {
    public static void main(String[] args) {
        Address newAddress = Address.builder()
                .houseNumber("219")
                .street("LLQ")
                .ward("3")
                .district("11")
                .province("HCM")
                .country("Vietnam")
                .build();
        User newUser = User.builder()
                .id(1)
                .name("CPP")
                .email("chphungphat@gmail.com")
                .phone("0867179650")
                .password("112233")
                .address(newAddress)
                .build();
        System.out.println(newUser.toString());
    }
}