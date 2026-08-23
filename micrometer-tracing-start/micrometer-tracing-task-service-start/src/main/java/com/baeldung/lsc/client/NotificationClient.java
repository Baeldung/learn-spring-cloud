package com.baeldung.lsc.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationClient {

    private final RestClient restClient;

    public NotificationClient(@Value("${notification-service.url}") String notificationServiceUrl) {
        this.restClient = RestClient.create(notificationServiceUrl);
    }

    public void sendNotification(String recipient, String subject, String content) {
        restClient.post()
            .uri("/notification/send")
            .body(new NotificationRequest("task-service", recipient, subject, content))
            .retrieve()
            .toBodilessEntity();
    }

    public record NotificationRequest(String sender, String recipient, String subject, String content) {
    }

}
