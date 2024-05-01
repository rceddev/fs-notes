package com.rree.fsnotes.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${auth.jwt.key}")
    private String privateKey;

    public String getToken(UserDetails user){
        return createToken(new HashMap<>(), user);
    }
    private String createToken(Map<String, Object> extraClaims, UserDetails user){
        return Jwts.builder()
                .claims()
                .add(extraClaims)
                .and()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey)))
                .compact();
    }

    public String getEmail(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private <T>T getClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return
            getEmail(token).equals(userDetails.getUsername())
                    && !isTokenExpired(getClaim(token, Claims::getExpiration))  ;
    }

    private boolean isTokenExpired(Date tokenDate) {
        return tokenDate.before(new Date());
    }
}

