package com.example.taskmanager.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.entity.Status;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000") // Allow React app origin
public class TaskController {

    private final TaskService taskService;

    // Inject TaskService into the controller using constructor-based dependency injection
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a new task
    @PostMapping // this annotation defines the POST HTTP method used to create new tasks
    public ResponseEntity<Task> createTask(@RequestBody Task task) { // RequestBody Task task binds the JSON request to a task object
        Task createdTask = taskService.saveTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED); // Return 201 status when task is created
    }

    // Get all tasks
    @GetMapping // this annotation defined the GET HTTP method to return a list of all tasks
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK); // Return 200 status with the list of tasks
    }

    // Get a specific task by ID
    @GetMapping("/{id}") // this maps to GET /api/tasks/{id} and retrieves a task by ID 
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) { // PathVariable binds the path variable {id} to the method parameter id
        Optional<Task> task = taskService.findTaskById(id);

        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK); // Return 200 status if task is found
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 status if task is not found
        }
    }

    // Get tasks filtered by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable Status status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        return new ResponseEntity<>(tasks, HttpStatus.OK); // Return 200 status with filtered tasks
    }

    // Get overdue tasks (due before a specific date)
    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks(@RequestParam("dueDate") String dueDateStr) { // RequestParam retrieves overdue tasks by comparing their due date with a provided date
        LocalDate dueDate = LocalDate.parse(dueDateStr); // Parse the date string into LocalDate
        List<Task> overdueTasks = taskService.getOverdueTasks(dueDate);
        return new ResponseEntity<>(overdueTasks, HttpStatus.OK); // Return 200 status with overdue tasks
    }

    // Update an existing task
    @PutMapping("/{id}") // this annotation defines the PUT HTTP method to update already existing tasks
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Optional<Task> taskOptional = taskService.findTaskById(id);

        if (taskOptional.isPresent()) {
            Task taskToUpdate = taskOptional.get();
            // Update task properties
            taskToUpdate.setTitle(taskDetails.getTitle());
            taskToUpdate.setDescription(taskDetails.getDescription());
            taskToUpdate.setStatus(taskDetails.getStatus());
            taskToUpdate.setPriority(taskDetails.getPriority());
            taskToUpdate.setDueDate(taskDetails.getDueDate());

            Task updatedTask = taskService.saveTask(taskToUpdate);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK); // Return 200 status with the updated task
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 status if task is not found
        }
    }

    // Delete a task by ID
    @DeleteMapping("/{id}") // this maps to DELETE /api/tasks/{id} and deletes the task with the requested ID
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> taskOptional = taskService.findTaskById(id);

        if (taskOptional.isPresent()) {
            taskService.deleteTaskById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 status when the task is deleted
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 status if task is not found
        }
    }
}


