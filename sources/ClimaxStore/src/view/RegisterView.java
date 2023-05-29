package view;

import builder.CustomerBuilder;
import entity.Address;
import entity.User;
import service.InputService;
import service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RegisterView {
    private static final RegisterView registerView = new RegisterView();
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private RegisterView() {}

    public static RegisterView getInstance() {
        return registerView;
    }

    public void displayRegisterMenu() {
        System.out.println("----------REGISTER----------");
        String name = InputService.getInstance().inputInfo("name");
        String email = "";
        boolean isExit = true;
        while (isExit) {
            isExit = false;
            email = InputService.getInstance().inputInfo("email");
            for (User user : UserService.getInstance().getUserList()) {
                if (email.equals(user.getEmail())) {
                    System.out.println("Email has been used. Try with another one");
                    isExit = true;
                }
            }
        }
        String phone = InputService.getInstance().inputInfo("phone");
        System.out.println("Enter password with following requirement\n" +
                        "1. Password must contain at least one digit [0-9]\n" +
                        "2. Password must contain at least one lowercase Latin character [a-z]\n" +
                        "3. Password must contain at least one uppercase Latin character [A-Z]\n" +
                        "4. Password must contain at least one special character like ! @ # & ( )\n" +
                        "5. Password must contain a length of at least 8 characters and a maximum of 20 characters");
        String password = InputService.getInstance().inputInfo("password");
        LocalDate birthDay = InputService.getInstance().inputBirthDate();
        Address address = InputService.getInstance().inputAddress();


        User newUser = CustomerBuilder.getInstance()
                .name(name)
                .email(email)
                .phone(phone)
                .password(password)
                .birthday(birthDay)
                .address(address)
                .wallet(0)
                .build();
        UserService.getInstance().addNewUser(newUser);
    }
}
