import React, { useState } from 'react';
import TaskService from '../services/TaskService';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import '../styles/TaskList.css'

const SearchTaskById = () => {
    const [taskId, setTaskId] = useState(''); // State for storing the entered task ID
    const [task, setTask] = useState(null); // State for storing the fetched task details
    const [error, setError] = useState(null); // State for handling errors

    const searchTask = async (e) => {
        e.preventDefault();

        try {
            // Fetch the task from the backend using the taskId
            const response = await TaskService.getTaskById(taskId);
            setTask(response.data); // Set the task data if found
            setError(null); // Clear any previous errors
        } catch (err) {
            console.error('Error fetching task:', err);
            setTask(null); // Clear the task if there was an error
            setError('Task not found. Please try again.'); // Set the error message
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
            <h2 className='search_main_title'>Search Task By ID</h2>
            <form className="search_form" onSubmit={searchTask}>
                <input
                    className="search_form_input"
                    type="text"
                    placeholder="Enter Task ID"
                    value={taskId}
                    onChange={(e) => setTaskId(e.target.value)}
                    required
                />
                <button className='search_submit' type="submit">Search</button>
            </form>

            {error && <div style={{ color: 'red' }}>{error}</div>}
            {task && (
                <div className="search_task_box">
                    <p className="search_title"><strong>ID:</strong> {task.id}</p>
                    <p className="search_title"><strong>Title:</strong> {task.title}</p>
                    <p className="search_title"><strong>Description:</strong> {task.description}</p>
                    <p className="search_title"><strong>Status:</strong> {task.status}</p>
                    <p className="search_title"><strong>Priority:</strong> {task.priority}</p>
                    <p className="search_title"><strong>Due Date:</strong> {task.dueDate}</p>
                </div>
            )}
        </div>
    );
};

export default SearchTaskById;
