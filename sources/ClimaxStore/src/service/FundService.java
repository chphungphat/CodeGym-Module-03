package service;

import entity.Customer;

public class FundService {
    private static final FundService fundService = new FundService();

    private FundService() {}

    public static FundService getInstance() {
        return fundService;
    }

    public void addFund(long amount) {
        long currentAmount = ((Customer) UserService.getInstance().getCurrentUser()).getWallet();
        ((Customer) UserService.getInstance().getCurrentUser()).setWallet(currentAmount + amount);
        System.out.println("Add fund successfully. Current fund is: " + ((Customer) UserService.getInstance().getCurrentUser()).getWallet());
    }

    public void subtractFund(long amount) {
        long currentAmout = ((Customer) UserService.getInstance().getCurrentUser()).getWallet();
        ((Customer) UserService.getInstance().getCurrentUser()).setWallet(currentAmout - amount);
        System.out.println("Fund subtracted successfully. Current fund is: " + ((Customer) UserService.getInstance().getCurrentUser()).getWallet());
    }
}
