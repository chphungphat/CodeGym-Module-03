package controller;

import model.CartProductDTO;
import model.Product;
import model.ShoppingCart;
import service.ProductService;
import service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class ShoppingCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default: {
                showCart(request, response);
                break;
            }
        }
    }

    public void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ShoppingCart> shoppingCartList = ShoppingCartService.getInstance().getAll();
        List<Product> productList = ProductService.getInstance().getProductList();
        List<CartProductDTO> cartList = CartProductDTO.getList(shoppingCartList, productList);
        float total = 0;
        for (CartProductDTO element : cartList) {
            total += element.getPrice();
        }
        request.setAttribute("cartList", cartList);
        request.setAttribute("total", total);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
