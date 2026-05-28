package com.baeldung.lsc.service;

import com.baeldung.lsc.persistence.model.Task;
import java.util.Optional;

public interface TaskService {
    Optional<Task> findById(Long id);

    Task save(Task task);
}
