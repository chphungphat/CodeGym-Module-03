package entity;

import builder.AddressBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Admin extends User {
    private static final Admin admin = new Admin(
            "Admin",
            "0867179650",
            "sudo",
            LocalDate.parse("02/01/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            AddressBuilder.getInstance()
                    .number("219")
                    .street("Lac Long Quan")
                    .ward("3")
                    .district("11")
                    .province("Ho Chi Minh city")
                    .country("Viet Nam")
                    .build(),
            "admin@climax.com"
    );

    private Admin(String name, String phone, String password, LocalDate birthday, Address address, String email) {
        super(name, phone, password, birthday, address, email);
        super.setId(0);
    }

    public static Admin getInstance() {
        return admin;
    }

    @Override
    public String toString() {
        return super.toString() + "\n";
    }
}
