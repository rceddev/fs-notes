package com.rree.fsnotes.auth.service;

import com.rree.fsnotes.auth.exception.CustomExceptionHandler;
import com.rree.fsnotes.auth.model.*;
import com.rree.fsnotes.auth.restclients.FSPersistanceClient;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

    @Autowired
    private UserDetailsService userDetailsService;

    Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthLoginResponse login(LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        }catch (BadCredentialsException e){
            logger.info("AUTH-ERROR: " + e.getMessage());
            throw new CustomExceptionHandler.WrongPasswordException(e.getMessage());
        }

        //TODO: Check if double check to the database its needed to gen the userDetails
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
        User userRegistered = persistanceClient.registUser(userToRegister);
        UserModel userModel = UserModel.builder()
                .id(userRegistered.getId())
                .email(userRegistered.getEmail())
                .firstName(userToRegister.getFirstName())
                .lastName(userRegistered.getLastName())
                .build();

        return AuthResponse.builder()
                .user(userModel)
                .token(jwtService.getToken(userToRegister))
                .build();
    }

    public ResponseEntity<String> validateToken(String token, HttpServletRequest request) {

        if (token == null) {
            return new ResponseEntity<>("Not token provided", HttpStatus.UNAUTHORIZED);
        }
        String userEmail = jwtService.getEmail(token);

        if (userEmail != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (!jwtService.isTokenValid(token, userDetails)) {
                return new ResponseEntity<>("Token not valid", HttpStatus.UNAUTHORIZED);
            }

            if ( SecurityContextHolder.getContext().getAuthentication() == null){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } else {
            return new ResponseEntity<>("Token not valid", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userEmail, HttpStatus.OK);
    }
}
