package view;

import entity.Customer;
import service.FundService;
import service.InputService;
import service.UserService;

public class AddFundView {
    private static final AddFundView addFundView = new AddFundView();

    private AddFundView() {}

    public static AddFundView getInstance() {
        return addFundView;
    }

    public void displayAddFundMenu() {
        long currentAmount = ((Customer) UserService.getInstance().getCurrentUser()).getWallet();
        System.out.println("Your current fund is: " +  currentAmount);
    }

    public void runAddFundMenu() {
        displayAddFundMenu();
        long amount = InputService.getInstance().inputFundAmount();
        FundService.getInstance().addFund(amount);
    }
}
