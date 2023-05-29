package service;

import entity.Game;
import entity.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private static final LibraryService libraryService = new LibraryService();

    private LibraryService() {
        libraryList = new ArrayList<>();
    }

    public static LibraryService getInstance() {
        return libraryService;
    }

    private List<Library> libraryList;
    private Library currentLibrary;

    public List<Library> getLibraryList() {
        return libraryList;
    }

    public void setLibraryList(List<Library> libraryList) {
        this.libraryList = libraryList;
    }

    public Library getCurrentLibrary() {
        return currentLibrary;
    }

    public void setCurrentLibrary(Library currentLibrary) {
        this.currentLibrary = currentLibrary;
    }

    public String printGame(int id) {
        String text = "Game not found";
        for (Game game : GameService.getInstance().getGameList()) {
            if (game.getId() == id) {
                text = id + ". " + game.getName();
            }
        }
        return text;
    }

    public void printBoughtGame() {
        System.out.println("List of bought games: ");
        for (int id : currentLibrary.getGameList()) {
            if (currentLibrary.getGameList().indexOf(id) != 0) {
                System.out.println(printGame(id));
            }
        }
    }

    public void printWishList() {
        System.out.println("Wish List: ");
        for (int id : currentLibrary.getWishList()) {
            System.out.println(printGame(id));
        }
    }

    public boolean isGameInWishList(int id) {
        return currentLibrary.getWishList().contains(id);
    }

    public void addGameToLibrary(List<Integer> gameIDList) {
        for (int id : gameIDList) {
            if (isGameInWishList(id)) {
                currentLibrary.getWishList().remove((Integer) id);
            } else {
                if (id == 0) {
                    currentLibrary.getGameList().add(id);
                }
            }
        }
        System.out.println("Games added to your library: ");
        for (int id : gameIDList) {
            System.out.println(GameService.getInstance().viewGameByID(id));
        }
    }

    public boolean isAddableToWishList(int id) {
        if ((currentLibrary.getGameList().indexOf(id) != 0) && (currentLibrary.getGameList().contains(id))) {
            System.out.println("Game already bought");
            return false;
        }
        if ((currentLibrary.getWishList().contains(id))) {
            System.out.println("Game in your wishlist");
            return false;
        }
        return true;
    }

    public void addGameToWishList() {
        int id = GameService.getInstance().getCurrentGame().getId();
        if (isAddableToWishList(id)) {
            currentLibrary.getWishList().add(id);
            System.out.println("Game added to wish list");
        }
    }

    public void removeGameFromWishList() {
        System.out.println("Choose a game to remove");
        int id = InputService.getInstance().inputChoice();
        if (isGameInWishList(id)) {
            currentLibrary.getWishList().remove((Integer) id);
            System.out.println("Game removed from wish list");
        } else {
            System.out.println("Game not found im wish list");
        }
    }

    public Library toLibrary(String[] data) {
        List<Integer> newGameList = new ArrayList<>();
        List<Integer> newWishList = new ArrayList<>();

        newGameList.add(Integer.parseInt(data[0]));
        int gameListSize = newGameList.get(0);

        if ((gameListSize == 0) && (data.length > 1)) {
            for (int index = 1; index < data.length; index++) {
                newWishList.add(Integer.parseInt(data[index]));
            }
            return new Library(newGameList, newWishList);
        } else if ((gameListSize == 0) && (data.length == 1)){
            return new Library(newGameList, newWishList);
        } else if ((gameListSize > 0) && (data.length == gameListSize + 1)) {
            for (int index = 1; index < data.length; index++) {
                newGameList.add(Integer.parseInt(data[index]));
            }
            return new Library(newGameList, newWishList);
        } else if ((gameListSize > 0) && (data.length > gameListSize + 1)) {
            for (int index = 1; index <= gameListSize; index++) {
                newGameList.add(Integer.parseInt(data[index]));
            }
            for (int index = gameListSize + 1; index < data.length; index++) {
                newWishList.add(Integer.parseInt(data[index]));
            }
            return new Library(newGameList, newWishList);
        }
        return new Library(newGameList, newWishList);
    }

    public void createNewLibrary() {
        Library newLibrary = new Library();
        libraryList.add(newLibrary);
        currentLibrary = libraryList.get(libraryList.size() - 1);
    }

    public void viewBoughtGameInfo() {
        System.out.println("Choose game you want to view:");
        int choice;
        while (true) {
            choice = InputService.getInstance().inputChoice();
            if ((!currentLibrary.getGameList().contains(choice))) {
                if (currentLibrary.getWishList().indexOf(choice) != 0) {
                    System.out.println("Invalid input");
                }
            } else {
                break;
            }
        }
        GameService.getInstance().setCurrentGame(GameService.getInstance().getGameList().get(choice - 1));
        Game currentGame = GameService.getInstance().getCurrentGame();
        System.out.println("Game info: ");
        System.out.println(currentGame.toString());
        System.out.println("Average rating: " + ReviewService.getInstance().getAverageRatingByGameID(currentGame.getId()));
    }

    public void viewWishedtGameInfo() {
        System.out.println("Choose game you want to view:");
        int choice;
        while (true) {
            choice = InputService.getInstance().inputChoice();
            if (!currentLibrary.getWishList().contains(choice)) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }
        GameService.getInstance().setCurrentGame(GameService.getInstance().getGameList().get(choice - 1));
        Game currentGame = GameService.getInstance().getCurrentGame();
        System.out.println("Game info: ");
        System.out.println(currentGame.toString());
        System.out.println("Average rating: " + ReviewService.getInstance().getAverageRatingByGameID(currentGame.getId()));
    }
}
