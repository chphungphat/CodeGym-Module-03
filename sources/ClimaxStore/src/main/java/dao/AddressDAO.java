package dao;

import model.user.Address;
import model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAO {
    private static final AddressDAO addressDAO = new AddressDAO();

    private AddressDAO() {}

    public static AddressDAO getInstance() {
        return addressDAO;
    }

    private final String SELECT_ADDRESS = "SELECT * FROM Address WHERE user_id = ?;";

    public Address getAddress(User user) {
        Address newAddress = null;
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESS)) {

            preparedStatement.setInt(1, user.getUser_id());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int address_id = rs.getInt("address_id");
                int user_id = rs.getInt("user_id");
                String houseNumber = rs.getString("houseNumber");
                String street = rs.getString("street");
                String ward = rs.getString("district");
                String province = rs.getString("province");
                String country = rs.getString("country");
                newAddress = Address.builder()
                        .address_id(address_id)
                        .user_id(user_id)
                        .houseNumber(houseNumber)
                        .street(street)
                        .ward(ward)
                        .province(province)
                        .country(country)
                        .build();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return newAddress;
    }
}
