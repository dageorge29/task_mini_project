package com.jmurillo.personal_task.service.impl;

import com.jmurillo.personal_task.dtos.request.TaskRequestDTO;
import com.jmurillo.personal_task.dtos.response.TaskResponseDTO;
import com.jmurillo.personal_task.entity.Priority;
import com.jmurillo.personal_task.entity.Status;
import com.jmurillo.personal_task.entity.Task;
import com.jmurillo.personal_task.mapper.TaskMapper;
import com.jmurillo.personal_task.repository.TaskRepository;
import com.jmurillo.personal_task.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;

    //Task repository DI
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    //Create a new task in db fields
    @Override
    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        Task t = mapper.toEntity(dto);
        return mapper.toDTO(taskRepository.save(t));
    }

    //find by costumer task by id in the db
    @Override
    public TaskResponseDTO readTaskByID(Long id) {
        return taskRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("The entity doenst exists"));
    }

    //retrieves all costumers
    @Override
    public List<TaskResponseDTO> readAll() {
        return taskRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    //updates all costumer data by id
    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO data) {
        Task gotIt = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Costumer id doesnt match with the provided"));

        if (gotIt != null){

            if (data.title() != null) {
                gotIt.setTitle(data.title());
            }
            if (data.description() != null){
                gotIt.setDescription(data.description());
            }
            if (data.priority() != null){
                gotIt.setPriority(data.priority());
            }
            if (data.status() != null){
                gotIt.setStatus(data.status());
            }
            Task saveIt = taskRepository.save(gotIt);
            return mapper.toDTO(saveIt);
        }
        Task saveIt = taskRepository.save(gotIt);
        return mapper.toDTO(saveIt);
    }

    @Override
    public String deleteTaskById(Long id) {
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return "id " + id + " deleted" ;
        }
        return "id " + id + " doesnt exist, pls try again ";
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }
}
