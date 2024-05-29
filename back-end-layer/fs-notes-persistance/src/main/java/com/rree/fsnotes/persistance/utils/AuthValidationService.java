package com.rree.fsnotes.persistance.utils;


import com.rree.fsnotes.persistance.model.AuthValidateTokenRequest;
import com.rree.fsnotes.persistance.restclient.FSAuthServerClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthValidationService {

    @Autowired
    private FSAuthServerClient fsAuthServerClient;
    public String validateToken(HttpServletRequest request){
        String userEmail;
        String token = getTokenFromRequest(request);
        try{
            userEmail = fsAuthServerClient.validateToken(new AuthValidateTokenRequest(token));
        }catch (Exception e){
            throw new NotAllowedException("Token not valid");
        }
        return userEmail;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }

        return null;
    }
}
