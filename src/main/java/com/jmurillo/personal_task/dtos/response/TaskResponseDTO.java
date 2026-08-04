package com.jmurillo.personal_task.dtos.response;

import com.jmurillo.personal_task.entity.Priority;
import com.jmurillo.personal_task.entity.Status;

public record TaskResponseDTO(

        Long id,
        String title,
        String description,
        Status status,
        Priority priority
) {}
