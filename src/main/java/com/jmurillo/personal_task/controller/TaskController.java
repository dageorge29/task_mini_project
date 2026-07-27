package com.jmurillo.personal_task.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        //ckeck existance of costumer id
        if (taskService.readTaskByID(id) != null){
            return taskService.readTaskByID(id);
        }
        return null;
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
    @PutMapping("/taskupdate/{id}")
    public void updateCostumerTaskById(@PathVariable(required = true) Long id, @RequestBody(required = true) Task task){
        taskService.updateTask(id, task);
    }

    //delete specific costumer task by id
    @DeleteMapping("/taskdelete/{id}")
    public String deleteTaskById(@PathVariable(required = true) Long id){
        if (taskService.deleteTask(id)){
            return "id: " + id + " eliminado";
        }
        return "id: " + id + " inexistente";
    }

    //delete all the costumers tasks
    @DeleteMapping("/deleteall")
    public void deleteAll(){
        taskService.deleteAll();
    }



}
