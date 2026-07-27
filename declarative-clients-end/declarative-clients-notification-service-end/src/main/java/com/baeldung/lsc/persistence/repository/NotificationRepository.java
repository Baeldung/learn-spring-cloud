package com.baeldung.lsc.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.baeldung.lsc.persistence.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
