package com.baeldung.lsc.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.baeldung.lsc.web.dto.Notification;
import com.baeldung.lsc.web.dto.NotificationRequest;

import feign.FeignException;

@Component
public class NotificationClientFallbackFactory implements FallbackFactory<NotificationClient> {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationClientFallbackFactory.class);

    @Override
    public NotificationClient create(Throwable cause) {
        NotificationClientFallback fallback = new NotificationClientFallback();
        return new NotificationClient() {

            @Override
            public Notification sendNotification(NotificationRequest request) {
                if (cause instanceof FeignException.BadRequest badRequest) {
                    LOG.warn("Notification call rejected as a business error, propagating: {}", cause.getMessage());
                    throw badRequest;
                }
                LOG.warn("Notification call failed, degrading gracefully: {}", cause.getMessage());
                return fallback.sendNotification(request);
            }

            @Override
            public Notification getNotification(Long id) {
                return fallback.getNotification(id);
            }

            @Override
            public List<Notification> getAllNotifications() {
                return fallback.getAllNotifications();
            }
        };
    }
}
