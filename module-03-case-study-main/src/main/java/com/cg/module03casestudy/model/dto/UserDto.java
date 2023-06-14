package com.cg.module03casestudy.model.dto;

public class UserDto {

    private Integer id;
    private String email;
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String phone;
    private String avatar;
    private Boolean activated;
    private Integer roleId;
    private String roleDesc;

    public UserDto() {
    }

    public UserDto(Integer id, String email, String username, String password, String fullname,
                   String address, String phone, String avatar, Boolean activated, Integer roleId) {
        super();
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.activated = activated;
        this.roleId = roleId;
    }

    public UserDto(String email, String username, String password, String fullname,
                   String address, String phone, String avatar, Boolean activated, Integer roleId) {
        super();
        this.email = email;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.activated = activated;
        this.roleId = roleId;
    }

    public UserDto(String email, String username, String password, String fullname, String address,
                   String phone, String avatar,  Boolean activated, Integer roleId, String roleDesc) {
        super();
        this.email = email;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.activated = activated;
        this.roleId = roleId;
        this.roleDesc = roleDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
