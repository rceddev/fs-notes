package com.rree.fsnotes.persistance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@GetMapping("/user")
	public String getUsers() {
		return "Hello World";
	}
}
