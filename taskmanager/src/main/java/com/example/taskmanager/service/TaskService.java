package com.example.taskmanager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.Status;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.repository.TaskRepository;

@Service
public class TaskService {
//The service layer is used by injecting the repository so that it can interact with the database
private final TaskRepository taskRepository;

@Autowired // Constructor
public TaskService(TaskRepository taskRepository){
    this.taskRepository = taskRepository;
}


//Create or update a task
public Task saveTask(Task task){
    return taskRepository.save(task);
    
}

//Find task by ID
public Optional<Task> findTaskById(Long id){
    return taskRepository.findById(id);
}

public Task getTaskById(Long id){
    return taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
}

//Get all Tasks
public List<Task> getAllTasks(){
    return taskRepository.findAll();
}

//Get tasks filtered by status
public List<Task> getTasksByStatus(Status status){
    return taskRepository.findByStatus(status);
}

//Get tasks filtered by due date
public List<Task> getOverdueTasks (LocalDate dueDate){
    return taskRepository.findByDueDateBefore(dueDate);
}

//Delete a task by ID
public void deleteTaskById(Long id){
     taskRepository.deleteById(id);
}

}
