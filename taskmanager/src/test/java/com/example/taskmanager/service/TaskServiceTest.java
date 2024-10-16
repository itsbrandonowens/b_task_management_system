package com.example.taskmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.repository.TaskRepository;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    
    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("Task Description");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.saveTask(task);

        assertNotNull(createdTask);
        assertEquals("New Task", createdTask.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

@Test
    public void testGetTaskById() {
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Existing Task");

        // Mock the behavior of taskRepository
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Call the method under test
        Task foundTask = taskService.getTaskById(taskId);

        // Verify the result
        assertNotNull(foundTask);
        assertEquals("Existing Task", foundTask.getTitle());
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    public void testGetTaskByIdNotFound() {
        Long taskId = 1L;

        // Mock the behavior of taskRepository to return an empty Optional
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Assert that the correct exception is thrown
        Exception exception = assertThrows(TaskNotFoundException.class, () -> {
            taskService.getTaskById(taskId);
        });

        // Verify the exception message
        assertEquals("Task not found with ID: " + taskId, exception.getMessage());
    }


}
