package service;

import model.User;

public class UserService {
    private static final UserService userService = new UserService();

    private UserService() {}

    public static UserService getInstance() {
        return userService;
    }

    private User currentUser;
}
