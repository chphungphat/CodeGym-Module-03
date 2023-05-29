package builder;

import entity.Game;
import entity.Review;

import java.time.LocalDate;
import java.util.List;

public interface IGameBuilder {
    IGameBuilder name(String name);
    IGameBuilder price(long price);
    IGameBuilder developer(String developer);
    IGameBuilder releaseDate(LocalDate releaseDate);
    IGameBuilder gametags(List<Integer> gametags);
    Game build();
}
