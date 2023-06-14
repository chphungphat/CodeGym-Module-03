package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final String SELECT_ALL_PRODUCT = "SELECT * FROM product;";

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                String image_url = rs.getString("image_url");
                Product product = Product.builder()
                        .id(id)
                        .name(name)
                        .price(price)
                        .description(description)
                        .image_url(image_url)
                        .build();
                products.add(product);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return products;
    }
}
