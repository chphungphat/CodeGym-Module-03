package controller;

import com.mysql.cj.Session;
import dao.UserDAO;
import model.user.User;
import model.user.User_Info;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/", "/home"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "": {}
            case "logout": {
                HttpSession session = request.getSession();
                session.setAttribute("userLogged", null);
                UserService.getInstance().setCurrentUser(null);
                UserService.getInstance().setCurrentUser_Info(null);
                UserService.getInstance().setCurrentAddress(null);
                showHomePage(request, response);
                break;
            }

            default: {
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
//                    response.sendRedirect(request.getContextPath() + "/home");
                    session.setAttribute("userLogged", user);
//                    request.setAttribute("userLogged", user);


                }
                request.getRequestDispatcher("home.jsp").forward(request, response);
                break;
            }
            case "register": {
                String user_name = request.getParameter("Username");
                String password = request.getParameter("registerPass");
                String email = request.getParameter("Email");
                String phone = request.getParameter("PhoneNumbers");
                User user = UserService.getInstance().UserRegister(user_name, password, email, phone);
                HttpSession session = request.getSession();
                if (user != null) {
                    session.setAttribute("userLogged", user);
//                    request.setAttribute("userLogged", user);
                }
                request.getRequestDispatcher("about.jsp").forward(request, response);
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
}
