package com.baeldung.lsc.web.controller;

import com.baeldung.lsc.service.EmailService;
import com.baeldung.lsc.web.dto.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationController.class);

    private EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public NotificationDto send(
        @RequestHeader(name = "traceparent", required = false) String traceparent,
        @RequestBody NotificationDto notification) {
        LOG.info("Incoming traceparent: {}", traceparent);
        emailService.send(notification);
        return notification;
    }

}
