package dao;

import model.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDAO {
    private final String ADD_TO_CART = "INSERT INTO shoppingCart(product_id, quantity) VALUES (?, 1);";
    private final String CHECK_EXIST = "SELECT * FROM shoppingCart WHERE product_id = ?;";
    private final String UPDATE_CART = "UPDATE shoppingCart SET quantity = quantity + 1 WHERE product_id = ?;";
    private final String SELECT_CART = "SELECT * FROM shoppingCart;";
    private final String GET_PRODUCT_NAME = "SELECT name FROM product WHERE id = ?";
    private final String GET_TOTAL_PRICE = "SELECT SUM(price) AS SUM FROM shoppingCart;";

    public boolean checkExist(int id) {
        boolean check = false;
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EXIST)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                check = true;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return check;
    }

    public void updateCart(int id) {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void addNewToCart(int id) {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_CART)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<ShoppingCart> getAll() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                ShoppingCart shoppingCart = ShoppingCart.builder()
                        .id(id)
                        .product_id(product_id)
                        .quantity(quantity)
                        .build();
                shoppingCarts.add(shoppingCart);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return shoppingCarts;
    }

    public String getName(int id) {
        String result = "";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_NAME)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result = rs.getString("name");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public float getTotalPrice() {
        float price = 0;
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TOTAL_PRICE)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                price = rs.getFloat("SUM");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return price;
    }
}
