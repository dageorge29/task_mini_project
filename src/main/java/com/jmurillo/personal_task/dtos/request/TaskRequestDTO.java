package com.jmurillo.personal_task.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jmurillo.personal_task.entity.Priority;
import com.jmurillo.personal_task.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO(

        Long id,

        @NotBlank(message = "must have contains a title")
        @Size(min = 5, max = 20, message = "must have contains between 5 and 20 characters")
        @JsonProperty("tas|k title")
        @Schema(example = "jorge")
        String title,

        @Size(min = 5, max = 40, message =  "must have contains between 5 and 40 characters")
        @JsonProperty("task description")
        @Schema(example = "nice to text here")
        String description,
        String hola,

        @NotNull(message = "it must has to be FINISHED OR STARTED")
        @JsonProperty("task status")
        @Schema(example = "FINISHED")
        Status status,

        @NotNull(message = "it must has to be LOW, MEDIUM OR HIGH ")
        @JsonProperty("task priority")
        @Schema(example = "LOW")
        Priority priority


) {
}
