package view;

import service.InputService;
import service.LibraryService;
import service.ReceiptService;
import service.UserService;

public class LibraryView {
    private static final LibraryView libraryView = new LibraryView();

    private LibraryView() {}

    public static LibraryView getInstance() {
        return libraryView;
    }

    private final int VIEW_BOUGHT_GAME = 1;
    private final int VIEW_WISH_LIST = 2;
    private final int VIEW_PURCHASE_HISTORY = 3;
    private final int GO_BACK = 4;

    public void displayLibraryMenu() {
        System.out.println("---------LIBRARY MENU---------");
        System.out.println("1. View bought game");
        System.out.println("2. View wishlist");
        System.out.println("3. View purchase history");
        System.out.println("4. Go back");
    }

    public void runLibraryMenu() {
        int choice = 0;
        while (choice != GO_BACK) {
            displayLibraryMenu();
            choice = InputService.getInstance().inputChoice();
            switch (choice) {
                case VIEW_BOUGHT_GAME -> {
                    LibraryService.getInstance().printBoughtGame();
                    System.out.println();
                    BoughtGameView.getInstance().runBoughtGameMenu();
                }
                case VIEW_WISH_LIST -> {
                    LibraryService.getInstance().printWishList();
                    System.out.println();
                    WishListView.getInstance().runWishListMenu();
                }
                case VIEW_PURCHASE_HISTORY -> {
                    ReceiptService.getInstance().viewReceiptByUserID(UserService.getInstance().getCurrentUser().getId());
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }
}
