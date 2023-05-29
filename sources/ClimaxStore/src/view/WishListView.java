package view;

import service.*;
import service.file_service.CartFileService;
import service.file_service.LibraryFileService;

public class WishListView {
    private static final WishListView wishListView = new WishListView();

    private WishListView() {}

    public static WishListView getInstance() {
        return wishListView;
    }

    private final int VIEW_GAME_INFO = 1;
    private final int REMOVE_GAME = 2;
    private final int VIEW_REVIEW = 3;
    private final int ADD_TO_CART = 4;
    private final int GO_BACK = 5;

    public void displayWishListMenu() {
        System.out.println("1. Choose a game to view info");
        System.out.println("2. Remove a game from wishlist");
        System.out.println("3. View reviews of a game");
        System.out.println("4. Add a game to cart");
        System.out.println("5. Go back");
    }

    public void runWishListMenu() {
        int choice = 0;
        while (choice != GO_BACK) {
            displayWishListMenu();
            choice = InputService.getInstance().inputChoice();
            switch (choice) {
                case VIEW_GAME_INFO -> {
                    LibraryService.getInstance().viewWishedtGameInfo();
                }
                case REMOVE_GAME -> {
                    LibraryService.getInstance().removeGameFromWishList();
                    LibraryFileService.getInstance().writeLibraryList();
                }
                case VIEW_REVIEW -> {
                    ReviewService.getInstance().displayReviewByChoice(LibraryService.getInstance().getCurrentLibrary().getWishList());
                }
                case ADD_TO_CART -> {
                    CartService.getInstance().addToCartByChoice(LibraryService.getInstance().getCurrentLibrary().getWishList());
                    CartFileService.getInstance().writeCartList();
                }
                default -> {
                    System.out.println("Invalid Input");
                }
            }
        }
    }
}
