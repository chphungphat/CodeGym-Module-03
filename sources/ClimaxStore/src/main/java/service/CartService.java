package service;

import controller.CartServlet;
import model.CartLine;
import model.product.Game;

import java.util.List;

public class CartService {
    private static final CartService cartService = new CartService();

    private CartService() {}

    public static CartService getInstance() {
        return cartService;
    }

//    public List<Game> getGameFromCart(List<CartLine> cartLineList) {
//
//    }
}
