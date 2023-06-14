package controller;

import dao.AddressDAO;
import dao.User_InfoDAO;
import model.user.Address;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogged");
        if (user == null) {
            response.sendRedirect("/home");
        } else {
            showProfilePage(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstNameInfo");
        String lastName = request.getParameter("lastNameInfo");
        String gender = request.getParameter("genderInfo");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = format.parse(request.getParameter("birthdayInfo"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogged");
        User_Info userInfo = User_Info.builder()
                .user_id(user.getUser_id())
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .gender(gender)
                .build();

        String houseNumber = request.getParameter("houseNumberInfo");
        String street = request.getParameter("streetInfo");
        String ward = request.getParameter("wardInfo");
        String district = request.getParameter("districtInfo");
        String province = request.getParameter("provinceInfo");
        String country = request.getParameter("countryInfo");
        Address address = Address.builder()
                .user_id(user.getUser_id())
                .houseNumber(houseNumber)
                .street(street)
                .ward(ward)
                .district(district)
                .province(province)
                .country(country)
                .build();

        UserService.getInstance().setCurrentUser_Info(userInfo);
        UserService.getInstance().setCurrentAddress(address);
        User_InfoDAO.getInstance().updateUserInfo(userInfo);
        AddressDAO.getInstance().updateAddress(address);

        session.setAttribute("userLoggedInfo", userInfo);
        session.setAttribute("userLoggedAddress", address);

        response.sendRedirect("profile");
    }

    private void showProfilePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);
    }
}
