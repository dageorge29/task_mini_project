package com.jmurillo.personal_task.service;

import com.jmurillo.personal_task.entity.Task;

import java.util.List;

public interface TaskService {

    //c
    public Task createTask(Task task);

    //r
    Task readTaskByID(Long id);
    List<Task> readAll();

    //u
    Task updateTask(Long id, Task task);

    //d
    Boolean deleteTask(Long id);

    void deleteAll();






}
