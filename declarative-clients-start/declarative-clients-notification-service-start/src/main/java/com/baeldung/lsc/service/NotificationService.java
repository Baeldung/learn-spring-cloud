package com.baeldung.lsc.service;

import java.util.List;
import java.util.Optional;

import com.baeldung.lsc.persistence.model.Notification;

public interface NotificationService {
    Notification create(Long taskId, String message);

    Optional<Notification> findById(Long id);

    List<Notification> findAll();
}
