package com.jmurillo.personal_task.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmurillo.personal_task.dtos.request.TaskRequestDTO;
import com.jmurillo.personal_task.dtos.response.TaskResponseDTO;
import com.jmurillo.personal_task.entity.Priority;
import com.jmurillo.personal_task.entity.Status;
import com.jmurillo.personal_task.entity.Task;
import com.jmurillo.personal_task.service.impl.TaskServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TaskController {

    private final TaskServiceImpl service;

    //retrieve costumer task by id if exists
    @GetMapping("/task/{id}")
    public TaskResponseDTO getById(@PathVariable(required = false) Long id){
        return service.readTaskByID(id);
    }

    //retrieve all costumers
    @GetMapping("/task")
    public List<TaskResponseDTO> getAllCostumers(){
        return service.readAll();
    }

    //create new Task
    @PostMapping("/task")
    public TaskResponseDTO createTask(@Valid @RequestBody(required = false) TaskRequestDTO task){
        return service.createTask(task);
    }

    //updates the costumer (all or majority) tasks datas
    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponseDTO> updateCostumerTaskById(@Valid @PathVariable Long id, @RequestBody TaskRequestDTO task){
        TaskResponseDTO t = service.updateTask(id, task);
        return ResponseEntity.ok(t);
    }

    //delete specific costumer task by id
    @DeleteMapping("/taskdelete/{id}")
    public ResponseEntity<Void> deleteTaskById(@Valid @PathVariable Long id){
        service.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //delete all the costumers tasks
    @DeleteMapping("/deleteall")
    public void deleteAll(){
        service.deleteAll();
    }
}
