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
    private final String UPDATE_ADDRESS = "UPDATE Address " +
            "SET houseNumber = ?, street = ?, ward = ?, district = ?, province = ?, country = ? " +
            "WHERE user_id = ?;";
    private final String INSERT_NEW_ADDRESS = "INSERT INTO Address(user_id) VALUES (?);";

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
                String ward = rs.getString("ward");
                String district = rs.getString("district");
                String province = rs.getString("province");
                String country = rs.getString("country");
                newAddress = Address.builder()
                        .address_id(address_id)
                        .user_id(user_id)
                        .houseNumber(houseNumber)
                        .street(street)
                        .ward(ward)
                        .district(district)
                        .province(province)
                        .country(country)
                        .build();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return newAddress;
    }

    public void updateAddress(Address address) {
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS)) {

            preparedStatement.setString(1, address.getHouseNumber());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getWard());
            preparedStatement.setString(4, address.getDistrict());
            preparedStatement.setString(5, address.getProvince());
            preparedStatement.setString(6, address.getCountry());
            preparedStatement.setInt(7, address.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void insertNewAddress(User user) {
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ADDRESS)) {

            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
