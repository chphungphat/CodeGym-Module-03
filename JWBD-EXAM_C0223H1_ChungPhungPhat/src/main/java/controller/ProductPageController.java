package controller;

import model.Product;
import service.ProductService;
import service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class ProductPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        showHomePage(request, response);
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "select": {
                addToCart(request, response);
                break;
            }
            case "cart": {
                response.sendRedirect("/cart");
                break;
            }
            default: {
                showHomePage(request, response);
                break;
            }
        }
    }

    private void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = ProductService.getInstance().getProductList();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ShoppingCartService.getInstance().addToCart(id);
        showHomePage(request, response);
    }


}
