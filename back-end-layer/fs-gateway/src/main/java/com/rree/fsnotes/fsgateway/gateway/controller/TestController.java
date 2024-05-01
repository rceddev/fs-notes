package com.rree.fsnotes.fsgateway.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rree.fsnotes.fsgateway.gateway.service.TestService;
import com.rree.fsnotes.fsgateway.model.AuthenticationUser;
import com.rree.fsnotes.fsgateway.model.UserResponse;



@Controller
public class TestController {
	
	@Autowired
	private TestService testSerice;
	
	@GetMapping("/test/{id}")
	public UserResponse getUserById(@PathVariable Integer id) {
		return testSerice.getUserById(id);
	}
}
