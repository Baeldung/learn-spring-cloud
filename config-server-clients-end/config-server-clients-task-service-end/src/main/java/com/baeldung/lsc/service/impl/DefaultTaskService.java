package com.baeldung.lsc.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.baeldung.lsc.persistence.model.Task;
import com.baeldung.lsc.persistence.repository.TaskRepository;
import com.baeldung.lsc.service.TaskService;

@Service
public class DefaultTaskService implements TaskService {
    private TaskRepository taskRepository;

    public DefaultTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }
}
