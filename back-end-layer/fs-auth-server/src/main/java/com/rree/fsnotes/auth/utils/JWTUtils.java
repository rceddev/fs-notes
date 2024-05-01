package com.rree.fsnotes.auth.utils;

import org.springframework.beans.factory.annotation.Value;

public class JWTUtils {

    @Value("${auth.jwt.key}")
    private String jwtKey;


}
