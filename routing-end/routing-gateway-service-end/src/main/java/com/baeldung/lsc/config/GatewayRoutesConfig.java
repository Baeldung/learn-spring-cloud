package com.baeldung.lsc.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

// This is the Java-DSL alternative to the YAML route table in application.yml.
// The YAML routes are the live configuration, so this bean is left inert (its
// @Configuration/@Bean annotations are commented out) to avoid registering the
// same two routes twice. To use the Java DSL instead, uncomment the two
// annotations below and remove the routes: block from application.yml.
//@Configuration
public class GatewayRoutesConfig {

    //@Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("task-service", r -> r.path("/campaigns/**").and().method(HttpMethod.GET).uri("http://localhost:8080"))
            .route("notification-service", r -> r.path("/notification/**").uri("http://localhost:8081"))
            .build();
    }

    // Illustrative only: OR/NOT predicate composition, not part of the live route table.
    //@Bean
    public RouteLocator advancedPredicateRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("task-service-legacy", r -> r.path("/campaigns/**").or().header("X-Legacy-Client", "true").uri("http://localhost:8080"))
            .route("task-service-external", r -> r.header("X-Internal", "true").negate().and().path("/campaigns/**").uri("http://localhost:8080"))
            .build();
    }
}
