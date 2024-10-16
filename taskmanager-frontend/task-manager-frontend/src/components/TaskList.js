import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import TaskService from '../services/TaskService';
import '../styles/TaskList.css'
const TaskList = () => {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        fetchTasks();
    }, []);

    const fetchTasks = async () => {
        try {
            const response = await TaskService.getAllTasks();
            setTasks(response.data);
        } catch (error) {
            console.error('Error fetching tasks:', error);
        }
    };

    const deleteTask = async (id) => {
        try {
            await TaskService.deleteTask(id);
            fetchTasks(); // Refresh the task list after deletion
        } catch (error) {
            console.error('Error deleting task:', error);
        }
    };

    return (
        <div>
            <h1 className="main_title">Task Manager</h1>
                <nav className="navbar">
                    <Link className="navbar_text" to="/">Task List</Link> 
                    <Link className="navbar_text" to="/create">Create Task</Link>
                    <Link className="navbar_text" to="/search">Search</Link>
                </nav>
            <h1 className="tasklist_title">Task List</h1>
            <ul>
                {tasks.map((task) => (
                    <li key={task.id} className="task_box">
                        <button className="delete_button" onClick={() => deleteTask(task.id)}>X</button>
                       <h2 className="task_title">  {task.title}  </h2>
                        <p className="task_description">{task.description}</p>
                        <span className="task_status"> {task.status} </span> <span className="task_priority">{task.priority}</span>
                        <p  className="task_due_date">Due Date: {task.dueDate}</p>
                        
                    </li>
                ))}
            </ul>

           
        </div>
    );
};

export default TaskList;