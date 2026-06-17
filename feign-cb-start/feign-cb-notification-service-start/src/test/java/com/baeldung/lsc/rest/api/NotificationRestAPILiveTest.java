package com.baeldung.lsc.rest.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.baeldung.lsc.persistence.model.Notification;
import com.baeldung.lsc.web.dto.NotificationRequest;

@SpringBootTest
public class NotificationRestAPILiveTest {

    private static final String BASE_URL = "http://localhost:8081/notifications";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void givenNewNotification_whenCreated_thenSuccess() {
        NotificationRequest request = new NotificationRequest(1L, "Task created: Test Task");
        ResponseEntity<Notification> response = restTemplate.postForEntity(BASE_URL, request, Notification.class);

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }

}
