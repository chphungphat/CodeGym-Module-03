package view;

import service.InputService;
import service.file_service.UserFileService;
import service.UserService;

public class UpdateInfoView {
    private static final UpdateInfoView updateInfoNew = new UpdateInfoView();

    private UpdateInfoView() {};

    public static UpdateInfoView getInstance() {
        return updateInfoNew;
    }

    private final int UPDATE_NAME = 1;
    private final int UPDATE_PHONE_NUMBER = 2;
    private final int UPDATE_BIRTHDAY = 3;
    private final int UPDATE_ADDRESS = 4;
    private final int EXIT = 5;

    public void displayUpdateInfoMenu() {
        System.out.println("----------UPDATE INFO MENU----------");
        System.out.println("1. Update name");
        System.out.println("2. Update phone number");
        System.out.println("3. Update birthday");
        System.out.println("4. Update address");
        System.out.println("5. Exit");
    }

    public void runUpdateInfoMenu() {
        int choice = 0;
        while (choice != EXIT) {
            displayUpdateInfoMenu();
            choice = InputService.getInstance().inputChoice();
            switch (choice) {
                case UPDATE_NAME -> {
                    UserService.getInstance().updateName();
                }
                case UPDATE_PHONE_NUMBER -> {
                    UserService.getInstance().updatePhone();
                }
                case UPDATE_BIRTHDAY -> {
                    UserService.getInstance().updateBirthday();
                }
                case UPDATE_ADDRESS -> {
                    UserService.getInstance().updateAddress();
                }
                case EXIT -> {
                    UserFileService.getInstance().writeUserList();
                }
                default -> System.out.println("Invalid input");
            }
        }


    }


}
