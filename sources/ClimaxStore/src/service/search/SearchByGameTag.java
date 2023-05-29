package service.search;

import entity.Game;
import service.GameService;
import service.InputService;

import java.util.ArrayList;
import java.util.List;

public class SearchByGameTag implements Search {
    private static final SearchByGameTag searchByGameTag = new SearchByGameTag();

    private SearchByGameTag() {}

    public static SearchByGameTag getInstance() {
        return searchByGameTag;
    }

    @Override
    public List<Integer> searchForGameByCondition() {
        return searchByGameTag();
    }

    public List<Integer> searchByGameTag() {
        List<Integer> gameID = new ArrayList<>();
        List<Integer> gameTagsList = InputService.getInstance().inputGameTagChoice();
        for (Game game : GameService.getInstance().getGameList()) {
            for (int gameTagsID : gameTagsList) {
                if (game.getGametags().contains(gameTagsID)) {
                    gameID.add(game.getId());
                }
            }
        }
        return gameID;
    }
}
