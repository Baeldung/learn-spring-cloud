package com.baeldung.lsc.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.baeldung.lsc.persistence.model.Notification;
import com.baeldung.lsc.service.NotificationService;
import com.baeldung.lsc.web.dto.NotificationRequest;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Notification create(@RequestBody NotificationRequest request) {
        return notificationService.create(request.taskId(), request.message());
    }

    @GetMapping("/{id}")
    public Notification findById(@PathVariable Long id) {
        return notificationService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Notification> findAll() {
        return notificationService.findAll();
    }
}
