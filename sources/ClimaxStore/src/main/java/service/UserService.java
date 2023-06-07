package service;

import dao.AddressDAO;
import dao.UserDAO;
import dao.User_InfoDAO;
import lombok.Getter;
import lombok.Setter;
import model.user.Address;
import model.user.User;
import model.user.User_Info;

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

    public void UserLogin(String loginName, String loginPass) {
        User loginUser = UserDAO.getInstance().checkLogin(loginName, loginPass);
        if (loginUser != null) {
            currentUser = loginUser;
            currentUser_Info = User_InfoDAO.getInstance().getUserInfo(loginUser);
            currentAddress = AddressDAO.getInstance().getAddress(loginUser);
        }
    }

    public static void main(String[] args) {
        String loginString = "gamemaster@admin.com";
        String loginPass = "sudo";
        UserService.userService.UserLogin(loginString, loginPass);
        System.out.println(UserService.getInstance().getCurrentUser());
    }
}
