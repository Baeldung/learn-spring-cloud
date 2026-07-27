package com.baeldung.lsc.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.baeldung.lsc.persistence.model.Task;
import com.baeldung.lsc.persistence.repository.TaskRepository;
import com.baeldung.lsc.service.TaskService;
import com.baeldung.lsc.web.dto.Notification;
import com.baeldung.lsc.web.dto.NotificationRequest;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    private TaskRepository taskRepository;
    private RestClient restClient;

    public TaskServiceImpl(TaskRepository taskRepository, RestClient restClient) {
        this.taskRepository = taskRepository;
        this.restClient = restClient;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        Task savedTask = taskRepository.save(task);
        Notification notification = restClient.post()
          .uri("http://localhost:8081/notifications")
          .body(new NotificationRequest(savedTask.getId(), "Task created: " + savedTask.getName()))
          .retrieve()
          .body(Notification.class);
        LOG.info("Notification sent: {}", notification);
        return savedTask;
    }
}
