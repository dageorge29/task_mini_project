package com.jmurillo.personal_task.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmurillo.personal_task.entity.Priority;
import com.jmurillo.personal_task.entity.Status;
import com.jmurillo.personal_task.entity.Task;
import com.jmurillo.personal_task.service.impl.TaskServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    //retrieve costumer task by id if exists
    @GetMapping("/task/{id}")
    public Task getById(@PathVariable(required = false) Long id){
        return taskService.readTaskByID(id);
    }

    //retrieve all costumers
    @GetMapping("/task")
    public List<Task> getAllCostumers(){
        return taskService.readAll();
    }

    //create new Task
    @PostMapping("/task")
    public Task createTask(@RequestBody(required = false) Task task){
        return taskService.createTask(task);
    }

    //updates the costumer (all or majority) tasks datas
    @PutMapping("/update/{id}")
    public Task updateCostumerTaskById(@PathVariable(required = true) Long id, @RequestBody(required = true) Task task){
        return taskService.updateTask(id, task);
    }

    //TODO: no funciona -> revisar params
    //updates the priority
    @PutMapping("/priority/{id}")
    public Task updateCostumerPriority(@PathVariable Long id, @RequestBody Priority priority){
        return taskService.updatePriority(id, priority);
    }

    //TODO: no funciona -> revisar param
    //updates the status
    @PutMapping("/status/{id}")
    public Task updateCostumerStatus(@PathVariable Long id, @RequestBody Status status){
        return taskService.updateStatus(id, status);
    }

    //delete specific costumer task by id
    @DeleteMapping("/taskdelete/{id}")
    public String deleteTaskById(@PathVariable Long id){
        return taskService.deleteTaskById(id);
    }

    //delete all the costumers tasks
    @DeleteMapping("/deleteall")
    public void deleteAll(){
        taskService.deleteAll();
    }



}
