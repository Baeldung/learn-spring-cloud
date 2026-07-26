package com.baeldung.lsc.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("task-service", r -> r.path("/api/campaigns/**").and().method(HttpMethod.GET)
                .filters(f -> f.addRequestHeader("X-Source", "gateway")
                    .addResponseHeader("X-Response-Source", "gateway")
                    .rewritePath("/api/(?<segment>.*)", "/${segment}")
                    .modifyResponseBody(String.class, String.class,
                        (exchange, body) -> Mono.just("{\"source\":\"gateway\",\"data\":" + body + "}")))
                .uri("http://localhost:8080"))
            .route("notification-service", r -> r.path("/notification/**")
                .filters(f -> f.modifyRequestBody(String.class, String.class,
                    (exchange, body) -> Mono.just(body.replace("\"sender\":null", "\"sender\":\"gateway\""))))
                .uri("http://localhost:8081"))
            .build();
    }
}
