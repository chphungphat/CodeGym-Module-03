package com.cg.module03casestudy.controller;

import com.cg.module03casestudy.model.dto.UserDto;
import com.cg.module03casestudy.model.service.RoleService;
import com.cg.module03casestudy.model.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userServlet",
            urlPatterns = {"/users", "/user/detail", "/user/add",
                            "/user/edit", "/user/remove"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = null;
    private RoleService roleService = null;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        roleService = new RoleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/users":
                req.setAttribute("users", userService.findAll());
                req.getRequestDispatcher("/WEB-INF/view/user/index.jsp").forward(req, resp);
                break;
            case "/user/detail":
                int detailId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("user", userService.find(detailId));
                req.getRequestDispatcher("/WEB-INF/view/user/detail.jsp").forward(req, resp);
                break;
            case "/user/add":
                req.setAttribute("roles", roleService.findAll());
                req.getRequestDispatcher("/WEB-INF/view/user/add.jsp").forward(req, resp);
                break;
            case "/user/edit":
                req.setAttribute("roles", roleService.findAll());
                int editId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("user", userService.find(editId));
                req.getRequestDispatcher("/WEB-INF/view/user/edit.jsp").forward(req, resp);
                break;
            case "/user/remove":
                int removeId = Integer.parseInt(req.getParameter("id"));
                userService.remove(removeId);
                resp.sendRedirect(req.getContextPath() + "/users");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        String roleIdStr = req.getParameter("roleId");
        Integer roleId = (roleIdStr != null) ? Integer.parseInt(roleIdStr) : null;

        String fullName = req.getParameter("fullname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String avatar = req.getParameter("avatar");
        Boolean activated = Boolean.valueOf(req.getParameter("activated"));

        UserDto userDto = new UserDto(email, username, password, fullName, address,
                phone, avatar, activated, roleId);

        switch (action) {
            case "/user/add":
                userService.add(userDto);
                resp.sendRedirect(req.getContextPath() + "/users");
                break;
            case "/user/edit":
                Integer id = Integer.parseInt(req.getParameter("id"));
                UserDto editingUserDto = userService.find(id);
                editingUserDto.setEmail(userDto.getEmail());
                editingUserDto.setUsername(userDto.getUsername());

                if (password != null) {
                    String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));
                    editingUserDto.setPassword(hashedPassword);
                }

                editingUserDto.setFullname(userDto.getFullname());
                editingUserDto.setAddress(userDto.getAddress());
                editingUserDto.setPhone(userDto.getPhone());
                editingUserDto.setAvatar(userDto.getAvatar());
                editingUserDto.setActivated(userDto.getActivated());
                editingUserDto.setRoleId(userDto.getRoleId());
                userService.edit(editingUserDto);
                resp.sendRedirect(req.getContextPath() + "/users");
                break;
        }
    }
}
