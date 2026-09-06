package com.baeldung.lsc.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.baeldung.lsc.messaging.TaskCompletedEvent;
import com.baeldung.lsc.persistence.model.Task;
import com.baeldung.lsc.persistence.model.TaskStatus;
import com.baeldung.lsc.persistence.repository.TaskRepository;
import com.baeldung.lsc.service.TaskService;

@Service
public class DefaultTaskService implements TaskService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultTaskService.class);

    private TaskRepository taskRepository;
    private StreamBridge streamBridge;

    public DefaultTaskService(TaskRepository taskRepository, StreamBridge streamBridge) {
        this.taskRepository = taskRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        Task saved = taskRepository.save(task);
        if (saved.getStatus() == TaskStatus.DONE) {
            streamBridge.send("taskCompleted", new TaskCompletedEvent(saved.getId(), saved.getName(), saved.getCampaign().getName()));
            LOG.info("Published task completed event for task {}", saved.getId());
        }
        return saved;
    }
}
