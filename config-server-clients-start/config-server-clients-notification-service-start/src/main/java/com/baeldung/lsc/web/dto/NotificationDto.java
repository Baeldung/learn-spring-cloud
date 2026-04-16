package com.baeldung.lsc.web.dto;

public record NotificationDto(
        String sender,
        String recipient,
        String subject,
        String content) {
}
