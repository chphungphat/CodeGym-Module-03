package dao;

import model.CartLine;
import model.product.Game;
import model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDAO {
    private static final CartDAO cartDAO = new CartDAO();

    private CartDAO() {}

    public static CartDAO getInstance() {
        return cartDAO;
    }

    private final String SELECT_ALL_CART_OF_USER = "SELECT * FROM CartLine WHERE user_id = ? AND isCheckOut = 0;";
    private final String SELECT_GAME_FROM_CART = "SELECT * FROM Game JOIN CartLine CL ON Game.game_id = CL.game_id AND CL.isCheckOut = 0 AND CL.user_id = ?;";
    private final String GET_CART_TOTAL_PRICE = "SELECT * FROM CartLine WHERE user_id = ? AND isCheckOut = 0 GROUP BY user_id;";
    private final String INSERT_NEW_CARTLINE = "INSERT INTO CartLine(user_id, game_id, price) VALUES (?, ?, ?);";
    private final String CHECK_OUT = "UPDATE CartLine SET isCheckOut = 1, check_out_date = now() WHERE user_id = ?;";
    private final String REMOVE_FROM_CATR = "DELETE FROM CartLine WHERE user_id = ? AND  game_id = ? AND isCheckout = 0;";

    public List<CartLine> selectCartOfUser(User user) {
        List<CartLine> cartLineList = new ArrayList<>();

        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CART_OF_USER)) {

            preparedStatement.setInt(1, user.getUser_id());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int cart_id = rs.getInt("cart_id");
                int user_id = rs.getInt("user_id");
                int game_id = rs.getInt("game_id");
                long price = rs.getLong("price");
                Date check_out_date = rs.getDate("check_out_date");

                CartLine cartLine = CartLine.builder()
                        .cart_id(cart_id)
                        .user_id(user_id)
                        .game_id(game_id)
                        .price(price)
                        .check_out_date(check_out_date)
                        .build();
                cartLineList.add(cartLine);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cartLineList;
    }

    public long getTotalPrice(User user) {
        long total_price = 0;
        try (Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_TOTAL_PRICE)) {

            preparedStatement.setInt(1, user.getUser_id());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                total_price = rs.getLong("price");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return total_price;
    }

    public void insertNewCartLine(User user, Game game) {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_CARTLINE)) {

            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.setInt(2, game.getGame_id());
            preparedStatement.setLong(3, game.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void checkOut(User user) {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_OUT)) {

            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removeFromCart(User user, Game game) {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_FROM_CATR)) {

            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.setInt(2, game.getGame_id());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

//    public List<Game> selectGameFromCart(User user) {
//        List<Game> gameList = new ArrayList<>();
//    }
}
