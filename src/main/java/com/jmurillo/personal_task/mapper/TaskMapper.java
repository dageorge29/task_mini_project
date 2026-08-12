package com.jmurillo.personal_task.mapper;

import com.jmurillo.personal_task.dtos.request.*;
import com.jmurillo.personal_task.dtos.response.TaskResponseDTO;
import com.jmurillo.personal_task.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskRequestDTO dto);
    TaskResponseDTO toDTO(Task dto);

}
