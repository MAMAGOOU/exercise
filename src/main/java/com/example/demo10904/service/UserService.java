package com.example.demo10904.service;

import com.example.demo10904.interceptor.RoleInterceptor;
import com.example.demo10904.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 19750
 * @version 1.0
 */
@Service
public class UserService {
    private static final Map<String, User> users = new HashMap<>();

    static {
        // 初始化一些示例用户
        users.put("1", new User("1", "user1", "password1", 1));
        users.put("2", new User("2", "admin1", "password2", 2));
    }

    public User authenticate(String username, String password) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User getCurrentUser() {
        return users.values().iterator().next();
    }

    public void validateUserAccess(String userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
    }

    public void deleteUser(String userId) {
        users.remove(userId);
    }

    public String generateToken(User user) {
        return "Bearer " + user.getId();
    }
}
