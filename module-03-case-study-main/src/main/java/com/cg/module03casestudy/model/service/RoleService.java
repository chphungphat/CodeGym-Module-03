package com.cg.module03casestudy.model.service;

import com.cg.module03casestudy.model.dao.RoleDao;
import com.cg.module03casestudy.model.dto.RoleDto;
import com.cg.module03casestudy.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleService {

    private RoleDao roleDao = null;

    public RoleService () {
        roleDao = new RoleDao();
    }

    public List<RoleDto> findAll() {
        List<RoleDto> roleDtos = new ArrayList<>();
        List<Role> roles = roleDao.getAll();

        for (Role role : roles) {
            roleDtos.add(new RoleDto(role.getId(), role.getName(), role.getDescription()));
        }

        return roleDtos;
    }

    public RoleDto find(int id) {
        Role role = roleDao.get(id);
        return new RoleDto(role.getId(), role.getName(), role.getDescription());
    }

    public void add(RoleDto roleDto) {
        Role role = new Role(roleDto.getName(), roleDto.getDescription());
        roleDao.insert(role);
    }

    public void edit(RoleDto roleDto) {
        Role role = new Role(roleDto.getId(), roleDto.getName(), roleDto.getDescription());
        roleDao.update(role);
    }
    public void remove(Integer id) {
        roleDao.delete(id);
    }
}
