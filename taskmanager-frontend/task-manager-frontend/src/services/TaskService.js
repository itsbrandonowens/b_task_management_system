import axios from 'axios';

const API_URL = 'http://localhost:8080/api/tasks';

class TaskService {
     // Create a new task
     createTask(task) {
        return axios.post(API_URL, task, {
            headers: {
                'Content-Type': 'application/json', // Ensure correct content type
            },
        });
    }

    // Get all tasks
    getAllTasks() {
        return axios.get(API_URL);
    }

    // Get a task by ID
    getTaskById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    // Update a task
    updateTask(id, task) {
        return axios.put(`${API_URL}/${id}`, task);
    }

    // Delete a task
    deleteTask(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new TaskService();