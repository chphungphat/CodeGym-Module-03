package view;

import service.*;
import service.file_service.*;

public class StartUpView {
    private static final StartUpView startUpView = new StartUpView();

    private StartUpView() {
        UserFileService.getInstance().readUserList();
        GameFileService.getInstance().readGameList();
        LibraryFileService.getInstance().readLibraryList();
        ReviewFileService.getInstance().readReviewList();
        CartFileService.getInstance().readCartList();
        ReceiptFileService.getInstance().readReceiptList();
    }

    public static StartUpView getInstance() {
        return startUpView;
    }

    private final int LOGIN = 1;
    private final int REGISTER = 2;
    private final int EXIT = 3;

    public void displayStartUpMenu() {
        System.out.println("-----------START UP MENU-----------");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }

    public void runStartUpMenu() {
        int choice = 0;
        while (choice != EXIT) {
            displayStartUpMenu();
            choice = InputService.getInstance().inputChoice();
            switch (choice) {
                case LOGIN -> {
                    if (LoginView.getInstance().displayLoginMenu()) {
                        if (UserService.getInstance().getCurrentUser().getId() == 0) {
                            AdminView.getInstance().runAdminMenu();
                        } else {
                            CustomerView.getInstance().runCustomerMenu();
                        }
                    } else {
                        System.out.println("Login unsuccessfully");
                    }
                }
                case REGISTER -> {
                    RegisterView.getInstance().displayRegisterMenu();
                    CustomerView.getInstance().runCustomerMenu();
                }
                case EXIT -> {
                    UserFileService.getInstance().writeUserList();
                    GameFileService.getInstance().writeGameList();
                    LibraryFileService.getInstance().writeLibraryList();
                    ReviewFileService.getInstance().writeReviewList();
                    CartFileService.getInstance().writeCartList();
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }
}
