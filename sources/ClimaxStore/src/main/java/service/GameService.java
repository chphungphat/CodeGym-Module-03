package service;

import dao.GameDAO;
import model.product.Game;

import java.util.List;

public class GameService {
    private static final GameService gameService = new GameService();

    private GameService() {}

    public static GameService getInstance() {
        return gameService;
    }

    private Game currentGame;

    public Game selectGame(int id) {
        Game game = GameDAO.getInstance().selectGameByID(id);
        return game;
    }

    public List<Game> gameOrderByPriceASC() {
        List<Game> gameList = GameDAO.getInstance().selectAllGamePriceASC();
        return gameList;
    }

    public List<Game> gameOrderByPriceDESC() {
        List<Game> gameList = GameDAO.getInstance().selectAllGamePriceDESC();
        return gameList;
    }

    public List<Game> findGameByString(String string) {
        List<Game> gameList = GameDAO.getInstance().findGameByString(string);
        return gameList;
    }
}
