package com.cg.module03casestudy.model.service;

import com.cg.module03casestudy.model.dao.UserDao;
import com.cg.module03casestudy.model.dto.LoginDto;
import com.cg.module03casestudy.model.dto.UserDto;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    private UserDao userDao = null;

    public AuthService () {
        userDao = new UserDao();
    }

    public UserDto login(String email, String password) {
        UserDto userDto = userDao.get(email);
        LoginDto loginDto = new LoginDto(
                userDto.getEmail(), userDto.getPassword());
        userDto.setRoleDesc("ROLE_ADMIN");
        boolean isCorrectPassword;
        if (loginDto == null) {
            return null;
        } else {
            isCorrectPassword = BCrypt.checkpw(password, loginDto.getPassword());
            if (isCorrectPassword) {
                return userDto;
            } else {
                return null;
            }
        }

//        return userDto;
    }
}
