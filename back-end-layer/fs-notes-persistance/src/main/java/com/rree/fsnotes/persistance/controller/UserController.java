package com.rree.fsnotes.persistance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rree.fsnotes.persistance.entity.User;
import com.rree.fsnotes.persistance.service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Integer id) {
		return userService.getUserById(id);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> saveUser( @Valid @RequestBody User user) {
		return userService.saveUser(user);
	}
	
}
