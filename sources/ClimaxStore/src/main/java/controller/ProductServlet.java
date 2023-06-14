package controller;

import dao.GameDAO;
import model.product.Game;
import model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("viewGame");
        String category = GameDAO.getInstance().getCategory(game);
        String developer = GameDAO.getInstance().getDeveloper(game);
        request.setAttribute("viewGameCate", category);
        request.setAttribute("viewGameDev", developer);
        User user = (User) session.getAttribute("userLogged");
        if (game == null) {
            response.sendRedirect("/home");
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default: {
                showProductDetail(request, response);
                break;
            }

        }
    }

    private void showProductDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }
}
