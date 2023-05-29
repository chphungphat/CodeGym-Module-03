package view;

import service.UserService;

import java.util.Scanner;

public class LoginView {
    private static final LoginView loginView = new LoginView();

    private LoginView() {}

    public static LoginView getInstance() {
        return loginView;
    }

    public boolean displayLoginMenu() {
        boolean check = false;
        System.out.println("----------LOGIN----------");
        System.out.print("Enter email: ");
        String inputEmail = new Scanner(System.in).nextLine();
        UserService userService = UserService.getInstance();
        if (userService.checkUser(inputEmail)) {
            System.out.print("Enter password: ");
            String inputPassword = new Scanner(System.in).nextLine();
            if (userService.checkPassword(inputPassword)) {
                System.out.println("Login success");
                check = true;
            } else {
                System.out.println("Wrong Password");
            }
        } else {
            System.out.println("User not exist");
        }
        return check;
    }
}

