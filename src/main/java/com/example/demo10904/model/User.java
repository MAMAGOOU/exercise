package com.example.demo10904.model;

/**
 * @author 19750
 * @version 1.0
 */
public class User {
    private String id;
    private String username;
    private String password;
    private int role;

    public User(String id, String username, String password, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }
}
