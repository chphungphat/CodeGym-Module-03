package view;

import entity.Game;
import service.*;
import service.file_service.CartFileService;
import service.file_service.LibraryFileService;
import service.file_service.ReviewFileService;

public class GameProductView {
    private static final GameProductView gameProductView = new GameProductView();

    private GameProductView() {}

    public static GameProductView getInstance() {
        return gameProductView;
    }

    private final int ADD_TO_CART = 1;
    private final int ADD_TO_WISHLIST = 2;
    private final int VIEW_REVIEW = 3;
    private final int EXIT = 4;

    public void displayGameProductMenu() {
        System.out.println("-------PRODUCT MENU---------");
        System.out.println("1. Add to cart");
        System.out.println("2. Add to wishlist");
        System.out.println("3. View reviews");
        System.out.println("4. Exit");
    }

    public void runGameProductMenu() {
        Game game = GameService.getInstance().getCurrentGame();
        int choice = 0;
        while (choice != EXIT) {
            displayGameProductMenu();
            choice = InputService.getInstance().inputChoice();
            switch (choice) {
                case ADD_TO_CART -> {
                    CartService.getInstance().addToCart();
                }
                case ADD_TO_WISHLIST -> {
                    LibraryService.getInstance().addGameToWishList();
                }
                case VIEW_REVIEW -> {
                    ReviewService.getInstance().displayReviewByGameID();
                }
                case EXIT -> {
                    CartFileService.getInstance().writeCartList();
                    LibraryFileService.getInstance().writeLibraryList();
                    ReviewFileService.getInstance().writeReviewList();

                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
            System.out.println();
        }
    }
}
