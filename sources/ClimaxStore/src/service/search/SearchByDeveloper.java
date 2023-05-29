package service.search;

import entity.Game;
import service.GameService;
import service.InputService;

import java.util.ArrayList;
import java.util.List;

public class SearchByDeveloper implements Search {
    private static final SearchByDeveloper searchByDeveloper = new SearchByDeveloper();

    private SearchByDeveloper() {}

    public static SearchByDeveloper getInstance() {
        return searchByDeveloper;
    }

    @Override
    public List<Integer> searchForGameByCondition() {
        return searchByDeveloper();
    }

    public List<Integer> searchByDeveloper() {
        List<Integer> gameID = new ArrayList<>();
        String dev = InputService.getInstance().inputDeveloper();
        for (Game game : GameService.getInstance().getGameList()) {
            if (game.getDeveloper() == dev) {
                gameID.add(game.getId());
            }
        }
        return gameID;
    }
}
