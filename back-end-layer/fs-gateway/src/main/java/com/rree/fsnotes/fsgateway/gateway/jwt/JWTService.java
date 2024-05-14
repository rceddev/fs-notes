package com.rree.fsnotes.fsgateway.gateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    @Value("${cloud.jwt.key}")
    private String privateKey;

    private <T>T getClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token) {
        boolean tokenValid;
        try{
            tokenValid = !isTokenExpired(getClaim(token, Claims::getExpiration))  ;
        }catch (Exception e){
            //TODO: Log failure trying to validate token
            return false;
        }
        return tokenValid;
    }

    private boolean isTokenExpired(Date tokenDate) {
        return tokenDate.before(new Date());
    }
}
