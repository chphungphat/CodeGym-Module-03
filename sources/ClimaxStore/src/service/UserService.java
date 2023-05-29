package service;

import entity.*;
import service.file_service.UserFileService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService userService = new UserService();

    private List<User> userList;

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String notification;
    private User currentUser;

    private UserService() {
        userList = new ArrayList<>();
        userList.add(Admin.getInstance());
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public boolean checkUser(String email) {
        notification = "Wrong email or password";
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                currentUser = user;
                if (user.getId() != 0) {
                    Library currentLibrary = LibraryService.getInstance().getLibraryList().get(user.getId() - 1);
                    Cart currentCart = CartService.getInstance().getCartList().get(user.getId() - 1);
                    LibraryService.getInstance().setCurrentLibrary(currentLibrary);
                    CartService.getInstance().setCurrentCart(currentCart);
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String password) {
        if (password.equals(currentUser.getPassword())) {
            notification = "Login successful";
            return true;
        } else {
            currentUser = null;
        }
        return false;
    }

    public void addNewUser(User newUser) {
        userList.add(newUser);
        UserFileService.getInstance().writeUserList();
        currentUser = userList.get(userList.size() - 1);
        LibraryService.getInstance().createNewLibrary();
        CartService.getInstance().createNewCart();
        System.out.printf("New customer info: \n" + ((Customer) newUser).toString());
        System.out.println();
    }

    public void viewUserInfo() {
        System.out.println("User info:");
        System.out.println(((Customer) currentUser).toString());
    }

    public void updateName() {
        String name = InputService.getInstance().inputInfo("name");
        currentUser.setName(name);
        System.out.println("Name has been changed to: " + currentUser.getName());
    }

    public void updatePhone() {
        String phone = InputService.getInstance().inputInfo("phone");
        currentUser.setPhone(phone);
        System.out.println("Phone number has been changed to " + currentUser.getPhone());
    }

    public void updateBirthday() {
        LocalDate date = InputService.getInstance().inputBirthDate();
        currentUser.setBirthday(date);
        System.out.println("Birthday has been changed to " + currentUser.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public void updateAddress() {
        Address address = InputService.getInstance().inputAddress();
        currentUser.setAddress(address);
        System.out.println("Address has been changed to " + currentUser.getAddress().toString());
    }

    public String getUserNameByID(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user.getName();
            }
        }
        return "User not found";
    }
}
