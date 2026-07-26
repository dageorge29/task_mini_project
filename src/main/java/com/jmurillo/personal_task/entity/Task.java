package com.jmurillo.personal_task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    //Entity: Task (id, title, description, isCompleted, dueDate, priority)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrementably id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "completed", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "priority", nullable = true)
    @Enumerated(EnumType.STRING)
    private Priority priority;

}
