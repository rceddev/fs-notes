package com.rree.fsnotes.persistance.controller;

import com.rree.fsnotes.persistance.entity.User;
import com.rree.fsnotes.persistance.service.UserService;
import com.rree.fsnotes.persistance.utils.AuthValidationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
public class SecureUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthValidationService authValidationService;

    @GetMapping("/user/{email}")
    public User getUserByEmail(@PathVariable String email, HttpServletRequest request) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user, HttpServletRequest request) {
        return userService.saveUser(user);
    }
}
