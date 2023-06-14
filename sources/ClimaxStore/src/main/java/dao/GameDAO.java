package dao;

import model.product.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameDAO {
    private static final GameDAO gameDAO = new GameDAO();

    private GameDAO() {}

    public static GameDAO getInstance() {
        return gameDAO;
    }

    private final String SELECT_ALL_GAME = "SELECT * FROM Game;";
    private final String SELECT_CATEGORY = "SELECT * FROM Category WHERE category_id = ?;";
    private final String SELECT_DEVELOPER = "SELECT * FROM Developer WHERE developer_id = ?;";
    private final String SELECT_GAME_BY_ID = "SELECT * FROM Game WHERE game_id = ?;";
    private final String SELECT_GAME_ORDER_BY_PRICE_ASC = "SELECT * FROM Game ORDER BY price ASC;";
    private final String SELECT_GAME_ORDER_BY_PRICE_DESC = "SELECT * FROM Game ORDER BY price DESC;";
    private final String FIND_GAME_BY_STRING = "SELECT * FROM Game WHERE gameName LIKE CONCAT( '%',?,'%') OR gameDescription LIKE CONCAT( '%',?,'%');";
    private final String INSERT_GAME = "INSERT INTO Game(gameName, price, releaseDate, gameDescription, gameImage, category_id, developer_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private final String SELECT_GAME_BY_CATEGORY_ID = "SELECT * FROM Game WHERE category_id = ?;";

    public List<Game> selectAllGamePriceASC() {
        List<Game> gameList = new ArrayList<>();
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GAME_ORDER_BY_PRICE_ASC)) {

            ResultSet rs = preparedStatement.executeQuery();

            while ((rs.next()) && (!rs.getBoolean("isDeleted"))){
                int game_id = rs.getInt("game_id");
                String gameName = rs.getString("gameName");
                long price = rs.getLong("price");
                Date releaseDate = rs.getDate("releaseDate");
                String gameDescription = rs.getString("gameDescription");
                String gameImage = rs.getString("gameImage");
                int category_id = rs.getInt("category_id");
                int developer_id = rs.getInt("developer_id");
                Game game = Game.builder()
                        .game_id(game_id)
                        .category_id(category_id)
                        .developer_id(developer_id)
                        .gameImage(gameImage)
                        .price(price)
                        .gameName(gameName)
                        .gameDescription(gameDescription)
                        .releaseDate(releaseDate)
                        .build();
                gameList.add(game);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return gameList;
    }

    public List<Game> selectAllGamePriceDESC() {
        List<Game> gameList = new ArrayList<>();
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GAME_ORDER_BY_PRICE_DESC)) {

            ResultSet rs = preparedStatement.executeQuery();

            while ((rs.next()) && (!rs.getBoolean("isDeleted"))){
                int game_id = rs.getInt("game_id");
                String gameName = rs.getString("gameName");
                long price = rs.getLong("price");
                Date releaseDate = rs.getDate("releaseDate");
                String gameDescription = rs.getString("gameDescription");
                String gameImage = rs.getString("gameImage");
                int category_id = rs.getInt("category_id");
                int developer_id = rs.getInt("developer_id");
                Game game = Game.builder()
                        .game_id(game_id)
                        .category_id(category_id)
                        .developer_id(developer_id)
                        .gameImage(gameImage)
                        .price(price)
                        .gameName(gameName)
                        .gameDescription(gameDescription)
                        .releaseDate(releaseDate)
                        .build();
                gameList.add(game);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return gameList;
    }

    public Game selectGameByID(int id) {
        Game game = null;
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GAME_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while ((rs.next()) && (!rs.getBoolean("isDeleted"))) {
                int game_id = rs.getInt("game_id");
                String gameName = rs.getString("gameName");
                long price = rs.getLong("price");
                Date releaseDate = rs.getDate("releaseDate");
                String gameDescription = rs.getString("gameDescription");
                String gameImage = rs.getString("gameImage");
                int category_id = rs.getInt("category_id");
                int developer_id = rs.getInt("developer_id");
                game = Game.builder()
                        .game_id(game_id)
                        .category_id(category_id)
                        .developer_id(developer_id)
                        .gameImage(gameImage)
                        .price(price)
                        .gameName(gameName)
                        .gameDescription(gameDescription)
                        .releaseDate(releaseDate)
                        .build();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return game;
    }

    public String getCategory(Game game) {
        String category = "";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY)) {

            preparedStatement.setInt(1, game.getCategory_id());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                category = rs.getString("categoryName");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return category;
    }

    public String getDeveloper(Game game) {
        String developer = "";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPER)) {

            preparedStatement.setInt(1, game.getDeveloper_id());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                developer = rs.getString("developerName");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return developer;
    }

    public List<Game> findGameByString(String string) {
        List<Game> gameList = new ArrayList<>();
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_GAME_BY_STRING)) {

            preparedStatement.setString(1, string);
            preparedStatement.setString(2, string);
            ResultSet rs = preparedStatement.executeQuery();

            while ((rs.next()) && (!rs.getBoolean("isDeleted"))){
                int game_id = rs.getInt("game_id");
                String gameName = rs.getString("gameName");
                long price = rs.getLong("price");
                Date releaseDate = rs.getDate("releaseDate");
                String gameDescription = rs.getString("gameDescription");
                String gameImage = rs.getString("gameImage");
                int category_id = rs.getInt("category_id");
                int developer_id = rs.getInt("developer_id");
                Game game = Game.builder()
                        .game_id(game_id)
                        .category_id(category_id)
                        .developer_id(developer_id)
                        .gameImage(gameImage)
                        .price(price)
                        .gameName(gameName)
                        .gameDescription(gameDescription)
                        .releaseDate(releaseDate)
                        .build();
                gameList.add(game);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return gameList;
    }

    public boolean insertGame(Game game) {
        boolean check = false;
        try (Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GAME)) {

            preparedStatement.setString(1, game.getGameName());
            preparedStatement.setLong(2, game.getPrice());
            preparedStatement.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            preparedStatement.setString(4, game.getGameDescription());
            preparedStatement.setString(5, game.getGameImage());
            preparedStatement.setInt(6, game.getCategory_id());
            preparedStatement.setInt(7, game.getDeveloper_id());

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                check = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return check;
    }
}
