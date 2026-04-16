package com.baeldung.lsc.rest.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.baeldung.lsc.web.dto.NotificationDto;

@SpringBootTest
public class NotificationRestAPILiveTest {

    private static final String BASE_URL = "http://localhost:8081/notification";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void givenNewNotification_whenSend_thenSuccess() {
        NotificationDto newNotification = new NotificationDto("sender@testemail.com",
                "recipient@testemail.com", "Test Email", "This is a test email.");
        ResponseEntity<NotificationDto> response = restTemplate.postForEntity(BASE_URL + "/send", newNotification, NotificationDto.class);

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("sender@testemail.com", response.getBody().sender());
    }

}
