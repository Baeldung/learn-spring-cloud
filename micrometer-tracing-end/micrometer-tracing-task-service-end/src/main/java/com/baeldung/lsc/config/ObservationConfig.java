package com.baeldung.lsc.config;

import org.springframework.boot.micrometer.observation.autoconfigure.ObservationRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.observation.ServerRequestObservationContext;

import io.micrometer.observation.ObservationPredicate;
import io.micrometer.observation.ObservationRegistry;

@Configuration
public class ObservationConfig {

    @Bean
    ObservationRegistryCustomizer<ObservationRegistry> observationRegistryCustomizer() {
        ObservationPredicate predicate = (name, context) -> {
            if (context instanceof ServerRequestObservationContext requestContext) {
                return !"/actuator/health".equals(
                    requestContext.getCarrier().getRequestURI());
            }
            return true;
        };
        return registry -> registry.observationConfig()
            .observationPredicate(predicate);
    }

}
