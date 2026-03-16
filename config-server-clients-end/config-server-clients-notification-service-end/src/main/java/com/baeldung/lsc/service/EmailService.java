package com.baeldung.lsc.service;

import com.baeldung.lsc.web.dto.NotificationDto;

public interface EmailService {
    void send(NotificationDto notification);
}
