package com.jmurillo.personal_task.controller;

import com.jmurillo.personal_task.entity.Task;
import com.jmurillo.personal_task.service.impl.TaskServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    //retrieve all costumers tasks if the required costumer id doesnt exists
    @GetMapping("/tasks")
    public List<Task> getAllTasks(@RequestParam(required = false) Long id){

        boolean exists = taskService.readAll().stream()
                .anyMatch(d -> d.getId() == id);

        if (exists != false){
            return new ArrayList<>();

        }
        return taskService.readAll();
    }

    /*
    @GetMapping("/tasks")
    public List<Task> getById(@RequestParam(required = false) Long id){
        return ;



    }

     */

}
