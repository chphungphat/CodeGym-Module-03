package com.cg.module03casestudy.model.service;

import com.cg.module03casestudy.model.dao.UserDao;
import com.cg.module03casestudy.model.dto.UserDto;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService {

    private UserDao userDao = null;

    public UserService() {
        userDao = new UserDao();
    }

    public List<UserDto> findAll() {
        return userDao.getAll();
    }

    public UserDto find(Integer id) {
        return userDao.get(id);
    }

    public void add(UserDto userDto) {
        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));
        UserDto newUserDto = new UserDto(userDto.getEmail(), userDto.getUsername(), hashedPassword,
                userDto.getFullname(), userDto.getAddress(), userDto.getPhone(), userDto.getAvatar(),
                userDto.getActivated(), userDto.getRoleId());
        userDao.insert(newUserDto);
    }

    public void edit(UserDto userDto) {
        UserDto existUserDto = new UserDto(userDto.getId(), userDto.getEmail(), userDto.getUsername(),
                userDto.getPassword(), userDto.getFullname(), userDto.getAddress(), userDto.getPhone(),
                userDto.getAvatar(), userDto.getActivated(), userDto.getRoleId());
        userDao.update(existUserDto);
    }

    public void remove(Integer id) {
        userDao.delete(id);
    }
}
