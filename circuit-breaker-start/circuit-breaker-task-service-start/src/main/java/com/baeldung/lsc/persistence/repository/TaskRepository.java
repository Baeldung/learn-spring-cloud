package com.baeldung.lsc.persistence.repository;

import com.baeldung.lsc.persistence.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {}
