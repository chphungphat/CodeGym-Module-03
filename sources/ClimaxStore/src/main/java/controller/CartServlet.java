package controller;

import dao.CartDAO;
import model.CartLine;
import model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WebServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
//            case "": {
//
//            }
            default: {
                showCart(request, response);
                break;
            }
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogged");
        if (user == null) {
            response.sendRedirect("/home");
        } else {
            List<CartLine> cartLineList = CartDAO.getInstance().selectCartOfUser(user);
            request.setAttribute("viewCart", cartLineList);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}
