package view;

import service.InputService;
import service.UserService;

public class UserInfoView {
    private static final UserInfoView userInfoView = new UserInfoView();

    private UserInfoView() {}

    public static UserInfoView getInstance() {
        return userInfoView;
    }

    private final int VIEW_INFO = 1;
    private final int UPDATE_INFO = 2;
    private final int EXIT = 3;

    public void displayUserInfoMenu() {
        System.out.println("----------USER INFO MENU----------");
        System.out.println("1. View Info");
        System.out.println("2. Update Info");
        System.out.println("3. Exit");
    }

    public void runUserInfoMenu() {
        int choice = 0;
        while (choice != EXIT) {
            displayUserInfoMenu();
            choice = InputService.getInstance().inputChoice();
            switch (choice) {
                case VIEW_INFO -> {
                    UserService.getInstance().viewUserInfo();
                }
                case UPDATE_INFO -> {
                    UpdateInfoView.getInstance().runUpdateInfoMenu();
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }
}
