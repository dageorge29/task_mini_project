package com.jmurillo.personal_task.service.impl;

import com.jmurillo.personal_task.entity.Task;
import com.jmurillo.personal_task.repository.TaskRepository;
import com.jmurillo.personal_task.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    //Task repository DI
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Create a new task in db fields
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    //find by costumer task by id in the db
    @Override
    public Task readTaskByID(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> readAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task gotIt = taskRepository.findById(id).orElse(null);

        if (gotIt != null){

            if (task.getTitle() != null) {
                gotIt.setTitle(task.getTitle());
            }
            if (task.getDescription() != null){
                gotIt.setDescription(task.getDescription());
            }
            if (task.getPriority() != null){
                gotIt.setPriority(task.getPriority());
            }
            if (task.getStatus() != null){
                gotIt.setStatus(task.getStatus());
            }
            return taskRepository.save(gotIt);
        }
        return null;
    }

    @Override
    public Boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }
}
