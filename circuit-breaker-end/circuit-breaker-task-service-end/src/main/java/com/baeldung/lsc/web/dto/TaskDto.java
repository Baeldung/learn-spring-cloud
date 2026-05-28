package com.baeldung.lsc.web.dto;

import com.baeldung.lsc.persistence.model.TaskStatus;
import java.time.LocalDate;

public record TaskDto(
    Long id,
    String name,
    String description,
    LocalDate dueDate,
    TaskStatus status,
    CampaignDto campaign
) {}
