package service;

import entity.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    private static final AddressService addressService = new AddressService();

    private AddressService() {
        addressList = new ArrayList<>();
    }

    public static AddressService getInstance() {
        return addressService;
    }

    private List<Address> addressList;
    private Address currentAddress;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }
}
