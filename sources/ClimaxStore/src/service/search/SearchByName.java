package service.search;

import entity.Game;
import service.GameService;
import service.InputService;

import java.util.ArrayList;
import java.util.List;

public class SearchByName implements Search {
    private static final SearchByName searchByName = new SearchByName();

    private SearchByName() {}

    public static SearchByName getInstance() {
        return searchByName;
    }

    @Override
    public List<Integer> searchForGameByCondition() {
        return searchByName();
    }

    private List<Integer> searchByName() {
        List<Integer> result = new ArrayList<>();
        String name = InputService.getInstance().inputInfo("name");
        for (Game game : GameService.getInstance().getGameList()) {
            if (game.getName().contains(name)) {
                result.add(game.getId());
            }
        }
        return result;
    }
}
