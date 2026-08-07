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
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    //Task repository DI
    public TaskServiceImpl(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Create a new task in db fields
    @Override
    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        Task t = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(t));
    }

    //find by costumer task by id in the db
    @Override
    public Optional<TaskResponseDTO> readTaskByID(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .stream()
                .findAny();
    }

    //retrieves all costumers
    @Override
    public List<TaskResponseDTO> readAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    //updates all costumer data by id
    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO data) {
        Task gotIt = repository.findById(id)
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
            Task saveIt = repository.save(gotIt);
            return mapper.toDTO(saveIt);
        }
        Task saveIt = repository.save(gotIt);
        return mapper.toDTO(saveIt);
    }

    @Override
    public Boolean deleteTaskById(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
