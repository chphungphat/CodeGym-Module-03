package builder;

import entity.Game;
import entity.Review;

import java.time.LocalDate;
import java.util.List;

public class GameBuilder implements IGameBuilder{
    protected String name;
    protected long price;
    protected String developer;
    protected LocalDate releaseDate;
    protected List<Integer> gametags;

    private static final GameBuilder gameBuilder = new GameBuilder();

    private GameBuilder() {}

    public static GameBuilder getInstance() {
        return gameBuilder;
    }

    @Override
    public IGameBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IGameBuilder price(long price) {
        this.price = price;
        return this;
    }

    @Override
    public IGameBuilder developer(String developer) {
        this.developer = developer;
        return this;
    }

    @Override
    public IGameBuilder releaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    @Override
    public IGameBuilder gametags(List<Integer> gametags) {
        this.gametags = gametags;
        return this;
    }

    @Override
    public Game build() {
        return new Game(name, price, developer, releaseDate, gametags);
    }

}
