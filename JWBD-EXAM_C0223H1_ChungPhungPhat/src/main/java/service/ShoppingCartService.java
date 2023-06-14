package service;

import dao.ProductDAO;
import dao.ShoppingCartDAO;
import model.Product;
import model.ShoppingCart;

import java.util.List;

public class ShoppingCartService {
    private static final ShoppingCartService shoppingCartService = new ShoppingCartService();
    private static final ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();

    private ShoppingCartService() {
    }

    public static ShoppingCartService getInstance() {
        return shoppingCartService;
    }

    public void addToCart(int id) {
        boolean check = shoppingCartDAO.checkExist(id);
        if (check) {
            shoppingCartDAO.updateCart(id);
        } else {
            shoppingCartDAO.addNewToCart(id);
        }
    }

    public List<ShoppingCart> getAll() {
        return shoppingCartDAO.getAll();
    }

    public String getName(int id) {
        return shoppingCartDAO.getName(id);
    }

    public float getTotalPrice() {
        return shoppingCartDAO.getTotalPrice();
    }
}
