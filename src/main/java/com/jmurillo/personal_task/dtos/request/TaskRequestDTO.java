package com.jmurillo.personal_task.dtos.request;

import com.jmurillo.personal_task.entity.Priority;
import com.jmurillo.personal_task.entity.Status;
import jakarta.persistence.EnumeratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO(

        @Positive
        Long id,

        @NotBlank(message = "must have contains a title")
        @Size(min = 5, message = "must have contains one character")
        @Size(max = 20, message = "it must have max 20 characters")
        String title,

        @Size(min = 5, message =  "must have contains one character")
        @Size(max = 40, message = "must have contains one character" )
        String description,

        @NotBlank(message = "it must has to be FINISHED OR STARTED")
        @NotNull
        Status status,

        @NotBlank(message = "it must has to be LOW, MEDIUM OR HIGH ")
        @NotNull
        Priority priority


) {
}
