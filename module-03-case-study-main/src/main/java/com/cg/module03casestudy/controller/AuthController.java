package com.cg.module03casestudy.controller;

import com.cg.module03casestudy.model.dto.UserDto;
import com.cg.module03casestudy.model.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "authServlet",
        urlPatterns = { "/login", "/logout" })
public class AuthController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AuthService authService = null;

    @Override
    public void init() throws ServletException {
        authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/login":
                req.getRequestDispatcher("/WEB-INF/view/login/index.jsp").forward(req, resp);
                break;
            case "/logout":
                if (req.getServletPath().startsWith("/logout")) {
                    req.getSession().removeAttribute("userLogged");
                }
                resp.sendRedirect(req.getContextPath() + "/login");
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String action = req.getServletPath();
        HttpSession session = req.getSession();

        switch (action) {
            case "/login":
                UserDto userDto = authService.login(email, password);
                if (userDto != null) {
                    session.setAttribute("userLogged", userDto);
                    resp.sendRedirect(req.getContextPath() + "/home");
                } else {
                    req.setAttribute("loginFailedMessage", "Invalid login info!");
                    req.getRequestDispatcher("/WEB-INF/view/login/index.jsp").forward(req, resp);
                }
                break;
            default:
                break;
        }
    }
}
