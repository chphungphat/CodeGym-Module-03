package entity;

import service.GameService;
import service.ReceiptService;
import service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Receipt {
    private int UserID;
    private LocalDateTime checkOutTime;
    private List<Integer> GameID;

    public Receipt(int userID, LocalDateTime checkOutTime, List<Integer> gameID) {
        UserID = userID;
        this.checkOutTime = checkOutTime;
        GameID = gameID;
    }

    public Receipt() {}

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public List<Integer> getGameID() {
        return GameID;
    }

    public void setGameID(List<Integer> gameID) {
        GameID = gameID;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String[] toArray() {
        int size = GameID.size() + 2;
        String[] newArray = new String[size];
        newArray[0] = String.valueOf(UserID);
        newArray[1] = checkOutTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        for (int index = 0; index < GameID.size(); index++) {
            newArray[index + 2] = String.valueOf(GameID.get(index));
        }
        return newArray;
    }
}
