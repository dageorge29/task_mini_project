package com.jmurillo.personal_task.mapper;

import com.jmurillo.personal_task.dtos.request.TaskRequestDTO;
import com.jmurillo.personal_task.dtos.response.TaskResponseDTO;
import com.jmurillo.personal_task.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponseDTO toDTO(Task entity) {
        if (entity == null) return null;

        return new TaskResponseDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getPriority()
        );
    }

    public Task toEntity(TaskRequestDTO dto){
        if (dto == null) return null;

        Task entity = new Task();
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setStatus(dto.status());
        entity.setPriority(dto.priority());

        return entity;
    }
}
