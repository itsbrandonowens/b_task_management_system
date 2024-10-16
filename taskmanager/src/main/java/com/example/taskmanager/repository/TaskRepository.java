package com.example.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.Status;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
// Extends JpaRepository inherits CRUD methods for the Task Entity
// Inherited methods include:  save(Task task), findById(Long id), findAll(), deleteById(Long id), count()
//<Task, Long> indicates that it will manage task entities and the type of the primary key(ID) is long

    //This method will retrieve a list of tasks filtered by status
    List<Task> findByStatus(Status status);

    //This method will retrieve a list of tasks filtered by priority
    List<Task> findByPriority(String priority);

    //This method will retrieve a list of tasks that are due before a certain date
    List<Task> findByDueDateBefore(LocalDate dueDate);
}
