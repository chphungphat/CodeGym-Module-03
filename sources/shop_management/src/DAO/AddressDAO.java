package DAO;

import entity.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDAO implements IAddressDAO{
    private static final AddressDAO addressDAO = new AddressDAO();

    private AddressDAO() {};

    public static AddressDAO getInstance() {
        return addressDAO;
    }

    private final String SELECT_ADDRESS_BY_ID = "SELECT * FROM Address WHERE address_id = ?;";
    private final String INSERT_ADDRESS_SQL = "INSERT INTO Address(houseNumber, street, ward, district, province, country) VALUES (?, ?, ?, ?, ?, ?);";

    @Override
    public void insertAddress(Address address) throws SQLException {
        System.out.println("Trying to insert address " + address.toString());
        try (Connection connection = ConnectionDB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADDRESS_SQL)) {

            preparedStatement.setString(1, address.getHouseNumber());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getWard());
            preparedStatement.setString(4, address.getDistrict());
            preparedStatement.setString(5, address.getProvince());
            preparedStatement.setString(6, address.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            ConnectionDB.getInstance().printSQLException(exception);
        }
    }

    @Override
    public Address selectAddress(int id) {
        Address address = null;
        try (Connection connection = ConnectionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESS_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int address_id = rs.getInt("address_id");
                String houseNumber = rs.getString("houseNumber");
                String street = rs.getString("street");
                String ward = rs.getString("ward");
                String district = rs.getString("district");
                String province = rs.getString("province");
                String country = rs.getString("country");

                address = Address.builder()
                        .address_id(address_id)
                        .houseNumber(houseNumber)
                        .street(street)
                        .ward(ward)
                        .district(district)
                        .province(province)
                        .country(country)
                        .build();
            }
        } catch (SQLException exception) {
            ConnectionDB.getInstance().printSQLException(exception);
        }
        return address;
    }

    @Override
    public List<Address> selectAllAddress() {
        return null;
    }

    @Override
    public boolean deleteAddress(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateAddress(Address address) throws SQLException {
        return false;
    }
}
