package com.baeldung.lsc.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baeldung.lsc.web.dto.Notification;
import com.baeldung.lsc.web.dto.NotificationRequest;

@FeignClient(name = "notification-service", url = "http://localhost:8081", 
  fallbackFactory = NotificationClientFallbackFactory.class)
public interface NotificationClient {

    @PostMapping("/notifications")
    Notification sendNotification(@RequestBody NotificationRequest request);

    @GetMapping("/notifications/{id}")
    Notification getNotification(@PathVariable Long id);

    @GetMapping("/notifications")
    List<Notification> getAllNotifications();
}
