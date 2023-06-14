package controller;

import dao.AddressDAO;
import dao.User_InfoDAO;
import model.product.Game;
import model.user.Address;
import model.user.User;
import model.user.User_Info;
import service.GameService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = {"/", "/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "profile": {
                response.sendRedirect("/profile");
                break;
            }
            case "logout": {
                HttpSession session = request.getSession();
                session.setAttribute("userLogged", null);
                session.setAttribute("userLoggedInfo", null);
                session.setAttribute("userLoggedAddress", null);
                UserService.getInstance().setCurrentUser(null);
                UserService.getInstance().setCurrentUser_Info(null);
                UserService.getInstance().setCurrentAddress(null);
                showHomePage(request, response);
                break;
            }
            case "select": {
                showProductPage(request, response);
                break;
            }
            case "search": {
                searchForGame(request, response);
                showHomePage(request, response);
                break;
            }
            case "cart": {
                response.sendRedirect("/cart");
                break;
            }
            default: {
                initailGameList(request, response);
                showHomePage(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("purpose");
        switch (action) {
            case "login": {
                String loginString = request.getParameter("loginString");
                String loginPass = request.getParameter("loginPass");
                User user = UserService.getInstance().UserLogin(loginString, loginPass);
                HttpSession session = request.getSession();
                if (user != null) {
                    User_Info userInfo = User_InfoDAO.getInstance().getUserInfo(user);
                    Address address = AddressDAO.getInstance().getAddress(user);
                    session.setAttribute("userLogged", user);
                    session.setAttribute("userLoggedInfo", userInfo);
                    session.setAttribute("userLoggedAddress", address);
                } else {
                    request.setAttribute("loginStatus", "failed");
                }
                request.getRequestDispatcher("home.jsp").forward(request, response);
                break;
            }
            case "register": {
                String user_name = request.getParameter("Username");
                String password = request.getParameter("registerPass");
                String confirmPass = request.getParameter("registerConfirmPass");
                String email = request.getParameter("Email");
                String phone = request.getParameter("PhoneNumbers");
                User user = null;
                if (confirmPass.equals(password)) {
                    user = UserService.getInstance().UserRegister(user_name, password, email, phone);

                }
                HttpSession session = request.getSession();
                if (user != null) {
                    User_Info userInfo = User_InfoDAO.getInstance().getUserInfo(user);
                    Address address = AddressDAO.getInstance().getAddress(user);
                    UserService.getInstance().setCurrentUser_Info(userInfo);
                    UserService.getInstance().setCurrentAddress(address);
                    session.setAttribute("userLogged", user);
                    session.setAttribute("userLoggedInfo", userInfo);
                    session.setAttribute("userLoggedAddress", address);
                } else {
                    request.setAttribute("registerStatus", "failed");
                }
                request.getRequestDispatcher("home.jsp").forward(request, response);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    private void showProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        Game game = GameService.getInstance().selectGame(id);
        session.setAttribute("viewGame", game);
        response.sendRedirect("/product");
    }

    private void initailGameList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Game> newGameList = GameService.getInstance().gameOrderByPriceDESC();
        session.setAttribute("gameList", newGameList);
    }

    private void searchForGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchString = request.getParameter("searchBar");
        List<Game> gameList = GameService.getInstance().findGameByString(searchString);
        if (gameList.size() > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("gameList", gameList);
        }
    }
}
