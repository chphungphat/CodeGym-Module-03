package service;

import entity.Game;
import entity.Receipt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReceiptService {
    private static final ReceiptService receiptService = new ReceiptService();

    private ReceiptService() {
        receiptList = new ArrayList<>();
    }

    public static ReceiptService getInstance() {
        return receiptService;
    }

    private List<Receipt> receiptList;

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

    public void printLastReceipt() {
        printReceipt(receiptList.size() - 1);
    }

    public void printReceipt(int index) {
        String userName = UserService.getInstance().getUserNameByID(receiptList.get(index).getUserID());
        String checkOutDate = receiptList.get(index).getCheckOutTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        long total = getTotalPay(receiptList.get(index));
        System.out.println("Receipt:");
        System.out.println("Customer name: " + userName);
        System.out.println("Checkout time: " + checkOutDate);
        System.out.println("Product List:");
        GameService.getInstance().displayGameListById(receiptList.get(index).getGameID());
        System.out.println("Total payment: " + total);
    }

    public long getTotalPay(Receipt receipt) {
        long total = 0;
        for (int game_ID : receipt.getGameID()) {
            for (Game game : GameService.getInstance().getGameList()) {
                if (game_ID == game.getId()) {
                    total += game.getPrice();
                    break;
                }
            }
        }
        return total;
    }

    public void viewReceiptByUserID(int userID) {
        for (int index = 0; index < receiptList.size(); index++) {
            if (receiptList.get(index).getUserID() == userID) {
                printReceipt(index);
            }
        }
    }

    public Receipt toReceipt(String[] data) {
        int user_id = Integer.parseInt(data[0]);
        LocalDateTime checkOutTime = LocalDateTime.parse(data[1], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        List<Integer> game_id = new ArrayList<>();
        for (int index = 2; index < data.length; index++) {
            game_id.add(Integer.parseInt(data[index]));
        }
        return new Receipt(user_id, checkOutTime, game_id);
    }
}
