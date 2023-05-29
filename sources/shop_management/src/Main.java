import DAO.AddressDAO;
import DAO.UserDAO;
import entity.Address;
import entity.Product;
import entity.User;
import service.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        String email = "mblanco@forbes.com";
//        String password = "jhdqKD8deQjw";
//
//        if (UserDAO.getInstance().loginToUser(email, password)) {
//            System.out.println("Login successfully");
//            System.out.println(UserService.getInstance().getCurrentUser().toString());
//        } else {
//            System.out.println("Login unsuccessfully");
//        }
        Address address = Address.builder()
                .address_id(0)
                .houseNumber("219")
                .street("LLQ")
                .ward("3")
                .district("11")
                .province("HCM")
                .country("VN")
                .build();

        AddressDAO.getInstance().insertAddress(address);

        User user = User.builder()
                .name("Chung Phùng Phát")
                .email("chphungphat@gmail.com")
                .phone("0704566907")
                .password("Qwerty!23456")
                .address(address)
                .build();

        UserDAO.getInstance().insertUser(user);
    }
}