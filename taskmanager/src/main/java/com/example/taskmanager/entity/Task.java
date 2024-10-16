package com.example.taskmanager.entity;

import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name="tasks")
public class Task {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generates a primary key
private Long id;

@Column(nullable = false) // Title is required
private String title;

@Column(length=500) // Optional desc with a length of 500 chars
private String description;

@Enumerated(EnumType.STRING) // Map enum to String in the database

private Status status;

@Enumerated(EnumType.STRING) // Map enum to String in the database

private Priority priority;

@Column(name = "due_date")
private LocalDate dueDate;

//Getters and Setters



    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Status return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return Priority return the priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * @return LocalDate return the dueDate
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}
