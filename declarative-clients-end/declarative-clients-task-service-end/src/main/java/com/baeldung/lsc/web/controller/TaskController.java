package com.baeldung.lsc.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.lsc.persistence.model.Task;
import com.baeldung.lsc.persistence.repository.TaskRepository;
import com.baeldung.lsc.service.TaskService;
import com.baeldung.lsc.web.dto.TaskDto;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    private TaskService taskService;
    private TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@RequestBody TaskDto newTask) {
        Task task = new Task(newTask.name(), newTask.description(), newTask.dueDate(), null);
        Task savedTask = taskService.save(task);
        return new TaskDto(savedTask.getId(), savedTask.getName(), savedTask.getDescription(), savedTask.getDueDate(),
                savedTask.getStatus(), null);
    }

    @GetMapping
    public Collection<TaskDto> findAll() {
        List<TaskDto> taskDtos = new ArrayList<>();
        taskRepository.findAll()
                .forEach(t -> taskDtos.add(new TaskDto(t.getId(), t.getName(), t.getDescription(), t.getDueDate(), t.getStatus(), null)));
        return taskDtos;
    }
}
