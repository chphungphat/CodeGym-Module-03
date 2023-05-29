package service;

import entity.Game;

public class AdminService {
    private static final AdminService adminService = new AdminService();

    private AdminService() {}

    public static AdminService getInstance() {
        return adminService;
    }

    public void addGame() {
        System.out.println("Enter new game info:");
        Game newGame = InputService.getInstance().inputGameInfo();
        GameService.getInstance().getGameList().add(newGame);
    }
}
