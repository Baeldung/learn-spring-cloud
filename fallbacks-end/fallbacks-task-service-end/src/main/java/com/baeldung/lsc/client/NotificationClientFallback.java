package com.baeldung.lsc.client;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baeldung.lsc.web.dto.Notification;
import com.baeldung.lsc.web.dto.NotificationRequest;

@Component
public class NotificationClientFallback implements NotificationClient {

    @Override
    public Notification sendNotification(NotificationRequest request) {
        return new Notification(null, request.taskId(), "Notification unavailable");
    }

    @Override
    public Notification getNotification(Long id) {
        return new Notification(id, null, "Notification unavailable");
    }

    @Override
    public List<Notification> getAllNotifications() {
        return List.of();
    }
}
