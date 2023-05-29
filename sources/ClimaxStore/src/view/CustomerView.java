package view;

import service.*;
import service.file_service.*;

public class CustomerView {
    private static final CustomerView customerView = new CustomerView();

    private CustomerView() {}

    public static CustomerView getInstance() {
        return customerView;
    }

    private final int USER_INFO = 1;
    private final int ADD_FUND = 2;
    private final int LIBRARY = 3;
    private final int BROWSE_SHOP = 4;
    private final int LOGOUT = 5;

    public void displayCustomerMenu() {
        System.out.println("-----------MAIN MENU-----------");
        System.out.println("1. User info");
        System.out.println("2. Add fund");
        System.out.println("3. Library");
        System.out.println("4. Browse shop");
        System.out.println("5. Logout");
    }

    public void runCustomerMenu() {
       int choice = 0;
       while (choice != LOGOUT) {
           displayCustomerMenu();
           choice = InputService.getInstance().inputChoice();
           switch (choice) {
               case USER_INFO -> {
                   UserInfoView.getInstance().runUserInfoMenu();
               }
               case ADD_FUND -> {
                   AddFundView.getInstance().runAddFundMenu();
                   UserFileService.getInstance().writeUserList();
               }
               case LIBRARY -> {
                    LibraryView.getInstance().runLibraryMenu();
               }
               case BROWSE_SHOP -> {
                    GameShopView.getInstance().runGameShopMenu();
               }
               case LOGOUT -> {
                   UserFileService.getInstance().writeUserList();
                   GameFileService.getInstance().writeGameList();
                   LibraryFileService.getInstance().writeLibraryList();
                   CartFileService.getInstance().writeCartList();
                   ReviewFileService.getInstance().writeReviewList();
               }
               default -> {
                   System.out.println("Invalid Input");
               }
           }
       }
    }
}
