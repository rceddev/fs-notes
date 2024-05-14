package com.rree.fsnotes.auth.controller;

import java.util.HashMap;
import java.util.Map;

import com.rree.fsnotes.auth.model.*;
import com.rree.fsnotes.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping(value = "login")
	public ResponseEntity<AuthLoginResponse> login(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(authService.login(loginRequest));
	}

	@PostMapping(value = "register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
		return ResponseEntity.ok(authService.register(registerRequest));
	}

	@PostMapping(value = "validateToken")
	public ResponseEntity<String> validateToken(@RequestBody AuthValidateTokenRequest validateTokenRequest, HttpServletRequest request){
		return authService.validateToken(validateTokenRequest.getToken(), request);
	}

	
}
