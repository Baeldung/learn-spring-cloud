package com.baeldung.lsc.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import io.micrometer.core.instrument.MeterRegistry;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ObservationConfigIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MeterRegistry meterRegistry;

    @Test
    void whenHealthIsRequested_thenOnlyApplicationRequestIsInstrumented() throws Exception {
        meterRegistry.clear();
        RestClient restClient = RestClient.create("http://localhost:" + port);

        assertThat(restClient.get()
            .uri("/actuator/health")
            .retrieve()
            .toBodilessEntity()
            .getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(restClient.get()
            .uri("/campaigns/1")
            .retrieve()
            .toBodilessEntity()
            .getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(meterRegistry.find("http.server.requests")
            .tag("uri", "/actuator/health")
            .timer()).isNull();
        assertThat(meterRegistry.find("http.server.requests")
            .tag("uri", "/campaigns/{id}")
            .timer()).isNotNull();
    }

}
