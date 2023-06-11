package service;

import dao.AddressDAO;
import dao.UserDAO;
import dao.User_InfoDAO;
import lombok.Getter;
import lombok.Setter;
import model.user.Address;
import model.user.User;
import model.user.User_Info;
import org.mindrot.jbcrypt.BCrypt;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserService {
    private static final UserService userService = new UserService();

    private UserService() {}

    public static UserService getInstance() {
        return userService;
    }

    private User currentUser;
    private User_Info currentUser_Info;
    private Address currentAddress;

    public User UserLogin(String loginString, String loginPass) {
        List<User> userList = UserDAO.getInstance().selectUser(loginString);
        User user = null;
        if (userList.size() > 0) {
            user = userList.get(0);
            if (checkPassword(user, loginPass)) {
                currentUser = user;
                currentUser_Info = User_InfoDAO.getInstance().getUserInfo(currentUser);
                currentAddress = AddressDAO.getInstance().getAddress(currentUser);
            } else {
                user = null;
            }
        }
        return user;
    }

    public User UserRegister(String userName, String password, String email, String phone) {
        User user = User.builder()
                .user_name(userName)
                .email(email)
                .phone(phone)
                .password(password)
                .created_date(new Date())
                .build();
        if (UserDAO.getInstance().insertUser(user)) {
            currentUser = user;
        } else {
            user = null;
        }
        return user;
    }

    public boolean checkPassword(User user, String loginPass) {
        return BCrypt.checkpw(loginPass, user.getPassword());
    }

}
