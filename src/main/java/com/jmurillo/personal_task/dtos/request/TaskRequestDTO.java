package com.jmurillo.personal_task.dtos.request;

import com.jmurillo.personal_task.entity.Priority;
import com.jmurillo.personal_task.entity.Status;
import jakarta.persistence.EnumeratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO(

        Long id,

        @NotBlank(message = "must have contains a title")
        @Size(min = 5, max = 20, message = "must have contains between 5 and 20 characters")
        String title,

        @Size(min = 5, max = 40, message =  "must have contains between 5 and 40 characters")
        String description,

        @NotNull(message = "it must has to be FINISHED OR STARTED")
        Status status,

        @NotNull(message = "it must has to be LOW, MEDIUM OR HIGH ")
        Priority priority


) {
}
