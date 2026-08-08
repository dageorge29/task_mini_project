package com.jmurillo.personal_task.service;

import com.jmurillo.personal_task.dtos.request.TaskRequestDTO;
import com.jmurillo.personal_task.dtos.response.TaskResponseDTO;
import com.jmurillo.personal_task.entity.Priority;
import com.jmurillo.personal_task.entity.Status;
import com.jmurillo.personal_task.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    //c
    TaskResponseDTO createTask(TaskRequestDTO task);

    //r
    TaskResponseDTO readTaskByID(Long id);
    List<TaskResponseDTO> readAll();

    //u
    TaskResponseDTO updateTask(Long id, TaskRequestDTO task);

    //d
    void deleteTaskById(Long id);

    void deleteAll();








}
