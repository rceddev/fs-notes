package com.rree.fsnotes.fsgateway.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/auth/**")
                        .uri("lb://FS-AUTH-SERVER"))
                .route(r -> r.path("/user/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://FS-PERSISTANCE"))
                .route(r -> r.path("/note/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://FS-PERSISTANCE"))
                .build();
    }
}
