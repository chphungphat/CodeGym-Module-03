package service;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final UserService userService =  new UserService();

    private UserService() {
        userList = new ArrayList<>();
    }

    public static UserService getInstance() {
        return userService;
    }

    private List<User> userList;
    private User currentUser;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void register() {

    }
}
