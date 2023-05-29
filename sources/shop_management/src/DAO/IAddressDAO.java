package DAO;

import entity.Address;

import java.sql.SQLException;
import java.util.List;

public interface IAddressDAO {
    void insertAddress(Address address) throws SQLException;

    Address selectAddress(int id);

    List<Address> selectAllAddress();

    boolean deleteAddress(int id) throws SQLException;

    boolean updateAddress(Address address) throws SQLException;
}
