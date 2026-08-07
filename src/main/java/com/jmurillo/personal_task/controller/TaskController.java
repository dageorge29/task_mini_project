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
public class TaskController {

    private final TaskServiceImpl service;

    public TaskController(TaskServiceImpl taskService) {
        this.service = taskService;
    }


    //retrieve costumer task by id if exists
    @GetMapping("/task/{id}")
    public ResponseEntity<Optional<TaskResponseDTO>> getById(@PathVariable(required = false) Long id){
        Optional<TaskResponseDTO> response = service.readTaskByID(id);

        if (response.isPresent()){
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    //retrieve all costumers
    @GetMapping("/task")
    public ResponseEntity<List<TaskResponseDTO>> getAllCostumers(){
        return ResponseEntity.ok(service.readAll());
    }

    //create new Task
    @PostMapping("/task")
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody(required = false) TaskRequestDTO task){
        TaskResponseDTO response = service.createTask(task);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
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
        Boolean deleted = service.deleteTaskById(id);

        if (deleted){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //delete all the costumers tasks
    @DeleteMapping("/deleteall")
    public void deleteAll(){
        service.deleteAll();
    }
}
