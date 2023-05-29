package view;

import service.AdminService;
import service.InputService;
import service.file_service.GameFileService;

public class AdminView {
    private static final AdminView adminView = new AdminView();

    private AdminView() {}

    public static AdminView getInstance() {
        return adminView;
    }

    private final int ADD_GAME = 1;
    private final int LOG_OUT = 2;

    public void displayAdminMenu() {
        System.out.println("-------ADMIN MENU--------");
        System.out.println("1. Add a game");
        System.out.println("2. Log out");
    }

    public void runAdminMenu() {
        int choice = 0;
        while (choice != LOG_OUT) {
            displayAdminMenu();
            choice = InputService.getInstance().inputChoice();
            switch (choice) {
                case ADD_GAME -> {
                    AdminService.getInstance().addGame();
                    GameFileService.getInstance().writeGameList();
                }
                default -> {
                    System.out.println("Invalid Input");
                }
            }
        }
    }
}
