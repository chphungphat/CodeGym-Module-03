package com.cg.module03casestudy.controller;

import com.cg.module03casestudy.model.dto.RoleDto;
import com.cg.module03casestudy.model.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "roleServlet",
        urlPatterns = {"/roles", "/role/detail", "/role/add",
                        "/role/edit", "/role/remove"})
public class RoleController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RoleService roleService = null;

    @Override
    public void init() {
        roleService = new RoleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/roles":
                req.setAttribute("roles", roleService.findAll());
                req.getRequestDispatcher("/WEB-INF/view/role/index.jsp").forward(req, resp);
                break;
            case "/role/detail":
                int detailId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("role", roleService.find(detailId));
                req.getRequestDispatcher("/WEB-INF/view/role/detail.jsp").forward(req, resp);
                break;
            case "/role/add":
                req.getRequestDispatcher("/WEB-INF/view/role/add.jsp").forward(req, resp);
                break;
            case "/role/edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("role", roleService.find(editId));
                req.getRequestDispatcher("/WEB-INF/view/role/edit.jsp").forward(req, resp);
                break;
            case "/role/remove":
                int removeId = Integer.parseInt(req.getParameter("id"));
                roleService.remove(removeId);
                resp.sendRedirect(req.getContextPath() + "/roles");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getServletPath();

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        RoleDto role = new RoleDto(name, description);

        switch (action) {
            case "/role/add":
                roleService.add(role);
                resp.sendRedirect(req.getContextPath() + "/roles");
                break;
            case "/role/edit":
                Integer id = Integer.valueOf(req.getParameter("id"));
                RoleDto editingRole = roleService.find(id);
                editingRole.setName(name);
                editingRole.setDescription(description);
                roleService.edit(editingRole);
                resp.sendRedirect(req.getContextPath() + "/roles");
                break;
        }
    }
}
