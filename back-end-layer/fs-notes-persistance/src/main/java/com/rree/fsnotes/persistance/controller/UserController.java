package com.rree.fsnotes.persistance.controller;

import com.rree.fsnotes.persistance.utils.AuthValidationService;
import jakarta.servlet.http.HttpServletRequest;
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

	@Autowired
	private AuthValidationService authValidationService;

	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Integer id, HttpServletRequest request) {
		authValidationService.validateToken(request);
		return userService.getUserById(id);
	}
	
}
