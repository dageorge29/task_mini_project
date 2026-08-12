package com.jmurillo.personal_task.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jmurillo.personal_task.entity.Priority;
import com.jmurillo.personal_task.entity.Status;

public record TaskResponseDTO(

        @JsonProperty("task id")
        Long id,

        @JsonProperty("task title")
        String title,

        @JsonProperty("task description")
        String description,

        @JsonProperty("task status")
        Status status,

        @JsonProperty("task priority")
        Priority priority
) {}
