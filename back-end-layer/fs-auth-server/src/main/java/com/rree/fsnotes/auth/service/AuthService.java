package com.rree.fsnotes.auth.service;

import com.rree.fsnotes.auth.model.*;
import com.rree.fsnotes.auth.restclients.FSPersistanceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {

    @Autowired
    private FSPersistanceClient persistanceClient;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthLoginResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetails userToLogin = persistanceClient.getUserByEmail(loginRequest.getEmail());
        String token = jwtService.getToken(userToLogin);
        return AuthLoginResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest){
        User userToRegister = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        return AuthResponse.builder()
                .user(persistanceClient.registUser(userToRegister))
                .token(jwtService.getToken(userToRegister))
                .build();
    }
}
