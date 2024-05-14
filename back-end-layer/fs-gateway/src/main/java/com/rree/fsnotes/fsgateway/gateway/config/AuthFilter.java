package com.rree.fsnotes.fsgateway.gateway.config;

import com.rree.fsnotes.fsgateway.gateway.jwt.JWTService;
import com.rree.fsnotes.fsgateway.gateway.restclients.FSAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GatewayFilter {

    @Autowired
    private JWTService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (authMissing(request))
            return onError(exchange);

        final String token = getTokenFromHeader(request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0));

        if (!jwtService.isTokenValid(token))
            return onError(exchange);

        return chain.filter(exchange);
    }


    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private String getTokenFromHeader(String authHeaderValue) {

        if (StringUtils.hasText(authHeaderValue) && authHeaderValue.startsWith("Bearer ")){
            return authHeaderValue.substring(7);
        }

        return null;
    }
}
