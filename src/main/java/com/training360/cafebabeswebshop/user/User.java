package com.training360.cafebabeswebshop.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {

    private long id;
    private String name;
    private String email;
    private String user_name;
    private String password;
    private String role;
    private String user_status;

    public User(long id, String name, String email, String user_name, String password, String role, String user_status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.user_name = user_name;
        this.password = new BCryptPasswordEncoder(4).encode(password);
        this.role = role;
        this.user_status = user_status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUser_status() {
        return user_status;
    }
}
