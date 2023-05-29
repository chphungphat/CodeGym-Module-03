package DAO;

import entity.User;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {


    void insertUser(User user) throws SQLException;

    User selectUser(int id);

    List<User> selectAllUsers();

    boolean loginToUser(String name, String password);

    boolean deleteUser(int id) throws SQLException;

    boolean updateUserInfo(User user) throws SQLException;

    boolean updateUserPassword(User user) throws SQLException;
}
