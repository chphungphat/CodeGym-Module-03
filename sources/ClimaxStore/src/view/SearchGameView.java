package view;

import service.*;
import service.file_service.CartFileService;
import service.file_service.LibraryFileService;
import service.search.SearchFactory;

import java.util.List;

public class SearchGameView {
    private static final SearchGameView searchGameView = new SearchGameView();

    private SearchGameView() {}

    public static SearchGameView getInstance() {
        return searchGameView;
    }

    private final int BY_NAME = 1;
    private final int BY_PRICE = 2;
    private final int BY_GAMETAGS = 3;
    private final int BY_DEVELOPER = 4;
    private final int GO_BACK = 5;

    public void displaySearchGameMenu() {
        System.out.println("--------SEARCH GAME MENU----------");
        System.out.println("1. Search by name");
        System.out.println("2. Search by price");
        System.out.println("3. Search by gametags");
        System.out.println("4. Search by developer");
        System.out.println("5. Go Back");
    }

    public void runSearchMenu() {
        int choice = 0;
        while (choice != GO_BACK) {
            displaySearchGameMenu();
            choice = InputService.getInstance().inputChoice();
            switch (choice) {
                case BY_NAME -> {
                    List<Integer> gameID = SearchFactory.getInstance().getSearch("name").searchForGameByCondition();
                    GameService.getInstance().displayGameListById(gameID);
                    System.out.println();
                    GameView.getInstance().runViewGameMenu();
                }
                case BY_PRICE -> {
                    List<Integer> gameID = SearchFactory.getInstance().getSearch("price").searchForGameByCondition();
                    GameService.getInstance().displayGameListByIDWithPrice(gameID);
                    System.out.println();
                    GameView.getInstance().runViewGameMenu();
                }
                case BY_GAMETAGS -> {
                    List<Integer> gameID = SearchFactory.getInstance().getSearch("gametags").searchForGameByCondition();
                    GameService.getInstance().displayGameListByIDWithTag(gameID);
                    System.out.println();
                    GameView.getInstance().runViewGameMenu();
                }
                case BY_DEVELOPER -> {
                    List<Integer> gameID = SearchFactory.getInstance().getSearch("developer").searchForGameByCondition();
                    GameService.getInstance().displayGameListByIDWithDeveloper(gameID);
                    System.out.println();
                    GameView.getInstance().runViewGameMenu();
                }
                case GO_BACK -> {
                    LibraryFileService.getInstance().writeLibraryList();
                    CartFileService.getInstance().writeCartList();
                }
            }
        }
    }
}
