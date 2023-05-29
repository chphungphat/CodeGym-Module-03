package service.search;

import entity.Game;
import service.GameService;
import service.InputService;

import java.util.ArrayList;
import java.util.List;

public class SearchByPrice implements Search{
    private static final SearchByPrice searchByPrice = new SearchByPrice();

    private SearchByPrice() {}

    public static SearchByPrice getInstance() {
        return searchByPrice;
    }

    @Override
    public List<Integer> searchForGameByCondition() {
        return searchByPrice();
    }

    public List<Integer> searchByPrice() {
        List<Integer> gameID = new ArrayList<>();
        double[] priceRange = InputService.getInstance().inputPriceRange();
        for (Game game : GameService.getInstance().getGameList()) {
            if (checkGameInPriceRange(priceRange, game)) {
                gameID.add(game.getId());
            }
        }
        return gameID;
    }

    public boolean checkGameInPriceRange(double[] priceRange, Game game) {
        double lower = priceRange[0];
        double upper = priceRange[1];
        if ((game.getPrice() <= upper) && (game.getPrice() >= lower)) {
            return true;
        } else {
            return false;
        }
    }
}
