package com.jmurillo.personal_task.controller;

import com.jmurillo.personal_task.dtos.request.TaskRequestDTO;
import com.jmurillo.personal_task.dtos.response.TaskResponseDTO;
import com.jmurillo.personal_task.service.impl.TaskServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody(required = false) TaskRequestDTO task){
        TaskResponseDTO response = service.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED)
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
        service.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //delete all the costumers tasks
    @DeleteMapping("/deleteall")
    public void deleteAll(){
        service.deleteAll();
    }
}
