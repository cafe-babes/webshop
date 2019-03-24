package com.training360.cafebabeswebshop.user;

public class UserInfo {

    private String role;
    private String userName;

    public UserInfo(String role, String userName) {
        this.role = role;
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
