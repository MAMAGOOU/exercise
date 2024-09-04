package com.example.demo10904.controller;

import com.example.demo10904.api.annotations.Role;
import com.example.demo10904.model.User;
import com.example.demo10904.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author 19750
 * @version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Role(value = Role.GUEST)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            String token = userService.generateToken(user); // 假设有一个方法来生成token
            return ResponseEntity.ok(token);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    @Role(value = Role.USER)
    @GetMapping("/user")
    public ResponseEntity<Void> getUser(@RequestParam String id) {
        userService.validateUserAccess(id); // 假设有一个方法来验证用户是否有权访问该资源
        return ResponseEntity.ok().build();
    }

    @Role(value = Role.ADMIN)
    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUser(@RequestParam String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
