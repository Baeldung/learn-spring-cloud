package com.baeldung.lsc.service.impl;

import com.baeldung.lsc.service.EmailService;
import com.baeldung.lsc.web.dto.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailService implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultEmailService.class);

    @Override
    public void send(NotificationDto notification) {
        LOG.info("SENDING EMAIL");
        LOG.info("From:    {}", notification.sender());
        LOG.info("To:      {}", notification.recipient());
        LOG.info("Subject: {}", notification.subject());
        LOG.info("Body:    {}", notification.content());
    }
}