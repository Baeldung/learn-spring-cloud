package com.baeldung.lsc.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baeldung.lsc.client.NotificationClient;
import com.baeldung.lsc.persistence.model.Task;
import com.baeldung.lsc.persistence.repository.TaskRepository;
import com.baeldung.lsc.service.TaskService;
import com.baeldung.lsc.web.dto.Notification;
import com.baeldung.lsc.web.dto.NotificationRequest;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    private TaskRepository taskRepository;
    private NotificationClient notificationClient;

    public TaskServiceImpl(TaskRepository taskRepository, NotificationClient notificationClient) {
        this.taskRepository = taskRepository;
        this.notificationClient = notificationClient;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        Task savedTask = taskRepository.save(task);
        Notification notification = notificationClient
                .sendNotification(new NotificationRequest(savedTask.getId(), "Task created: " + savedTask.getName()));
        LOG.info("Notification sent: {}", notification);
        return savedTask;
    }
}
