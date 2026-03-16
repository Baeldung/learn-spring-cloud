package com.baeldung.lsc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.lsc.service.EmailService;
import com.baeldung.lsc.web.dto.NotificationDto;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    private EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public NotificationDto send(@RequestBody NotificationDto notification) {
        emailService.send(notification);
        return notification;
    }

}