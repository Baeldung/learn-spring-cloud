package com.baeldung.lsc.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.baeldung.lsc.client.NotificationClient;
import com.baeldung.lsc.persistence.model.Task;
import com.baeldung.lsc.persistence.model.TaskStatus;
import com.baeldung.lsc.persistence.repository.TaskRepository;
import com.baeldung.lsc.service.TaskService;

@Service
public class DefaultTaskService implements TaskService {

    private TaskRepository taskRepository;
    private NotificationClient notificationClient;

    public DefaultTaskService(TaskRepository taskRepository, NotificationClient notificationClient) {
        this.taskRepository = taskRepository;
        this.notificationClient = notificationClient;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        Task saved = taskRepository.save(task);
        if (TaskStatus.DONE.equals(saved.getStatus())) {
            notificationClient.sendNotification(
                "team@example.com",
                "Task Completed: " + saved.getName(),
                "The task '" + saved.getName() + "' has been marked as done.");
        }
        return saved;
    }
}
