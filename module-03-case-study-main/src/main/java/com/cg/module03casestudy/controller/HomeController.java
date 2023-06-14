package com.cg.module03casestudy.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "homeServlet",
        urlPatterns = { "/", "/home" })
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/":
            case "/home":
                req.getRequestDispatcher("/WEB-INF/view/home/index.jsp").forward(req, resp);
                break;
            case "/error/403":
                req.getRequestDispatcher("/WEB-INF/view/error/403.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }
}
