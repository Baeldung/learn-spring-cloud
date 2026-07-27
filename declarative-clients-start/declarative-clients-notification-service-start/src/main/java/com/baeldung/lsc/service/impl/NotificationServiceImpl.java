package com.baeldung.lsc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baeldung.lsc.persistence.model.Notification;
import com.baeldung.lsc.persistence.repository.NotificationRepository;
import com.baeldung.lsc.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification create(Long taskId, String message) {
        LOG.info("Creating notification for task {}: {}", taskId, message);
        Notification notification = new Notification(taskId, message);
        return notificationRepository.save(notification);
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public List<Notification> findAll() {
        return StreamSupport.stream(notificationRepository.findAll().spliterator(), false).toList();
    }
}
