package com.baeldung.lsc.messaging;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baeldung.lsc.service.EmailService;
import com.baeldung.lsc.web.dto.NotificationDto;

@Configuration
public class NotificationFunctions {

    private EmailService emailService;

    public NotificationFunctions(EmailService emailService) {
        this.emailService = emailService;
    }

    @Bean
    public Consumer<TaskCompletedEvent> sendNotification() {
        return event -> {
            NotificationDto notification = new NotificationDto("tasks@baeldung.com", "team@baeldung.com", "Task completed: " + event.taskName(),
                event.taskName() + " in campaign " + event.campaignName() + " is now done.");
            emailService.send(notification);
        };
    }
}
