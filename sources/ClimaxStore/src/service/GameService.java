package service;

import entity.Game;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private static final GameService gameService = new GameService();

    private GameService() {
        gameList = new ArrayList<>();
    }

    public static GameService getInstance() {
        return gameService;
    }

    private List<Game> gameList;
    private Game currentGame;

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void addNewGame(Game newGame) {
        gameList.add(newGame);
        currentGame = gameList.get(gameList.size()- 1);
        System.out.println("New Game info: \n" + newGame.toString());
        System.out.println();
    }

    public void viewGameList() {
        System.out.println("--------LIST GAMES----------");
        for (Game game : gameList) {
            System.out.println(game.getId() + ". " + game.getName());
        }
    }

//    public void viewGameListByID(List<Integer> gameIDList) {
//        for (Game game : gameList) {
//            for (int id : gameIDList) {
//                if (game.getId() == id) {
//                    System.out.println(game.getId() + ". " + game.getName());
//                    break;
//                }
//            }
//        }
//    }

    public String viewGameByID(int id) {
        for (Game game : gameList) {
            if (game.getId() == id) {
                return id + ". " + game.getName();
            }
        }
        return "Game not found";
    }

    public void viewGameInfo() {
        System.out.println("Choose game you want to view:");
        int choice;
        while (true) {
            choice = InputService.getInstance().inputChoice();
            if (choice < 1 || choice > gameList.size()) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }
        System.out.println();
        currentGame = gameList.get(choice - 1);
        System.out.println("Game info: ");
        System.out.println(currentGame.toString());
        System.out.println("Average rating: " + ReviewService.getInstance().getAverageRatingByGameID(currentGame.getId()));
    }

    public void displayGameListById(List<Integer> gameID) {
        if (gameID.isEmpty()) {
            System.out.println("No such game");
        } else {
            for (Game game : gameList) {
                for (Integer element : gameID) {
                    if (game.getId() == element) {
                        System.out.println(element + ". " + game.getName());
                        break;
                    }
                }
            }
        }
    }

    public void displayGameListByIDWithTag(List<Integer> gameID) {
        if (gameID.isEmpty()) {
            System.out.println("No such game");
        } else {
            for (Game game : gameList) {
                for (Integer element : gameID) {
                    if (game.getId() == element) {
                        System.out.println(element + ". " + game.getName() + " " + game.printGametags());
                        break;
                    }
                }
            }
        }
    }

    public void displayGameListByIDWithPrice(List<Integer> gameID) {
        if (gameID.isEmpty()) {
            System.out.println("No such game");
        } else {
            for (Game game : gameList) {
                for (Integer element : gameID) {
                    if (game.getId() == element) {
                        System.out.println(element + ". " + game.getName() + " " + game.getPrice());
                        break;
                    }
                }
            }
        }
    }

    public void displayGameListByIDWithDeveloper(List<Integer> gameID) {
        if (gameID.isEmpty()) {
            System.out.println("No such game");
        } else {
            for (Game game : gameList) {
                for (Integer element : gameID) {
                    if (game.getId() == element) {
                        System.out.println(element + ". " + game.getName() + " " + game.getDeveloper());
                        break;
                    }
                }
            }
        }
    }

    public String getGameNameByID(int id) {
        for (Game game : gameList) {
            if (game.getId() == id) {
                return game.getName();
            }
        }
        return "Game not found";
    }
}
