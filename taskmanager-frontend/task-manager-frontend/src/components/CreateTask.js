import React, { useState } from 'react';
import TaskService from '../services/TaskService';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
// add ability to get task by ID
// add ability to update a tasks details

const CreateTask = () => {
    const today = new Date();
    const currentDate = today.getFullYear() + "-" + (today.getMonth()+1) + "-"+ today.getDate();
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [dueDate, setDueDate] = useState(currentDate);
    const [status, setStatus] = useState(''); 
    const [priority, setPriority] = useState(''); 
    const [error, setError] = useState(null);
    


    const createTask = async (e) => {
        e.preventDefault();
        const task = { title, description, dueDate, status, priority }; 

        try {
            await TaskService.createTask(task);
            setTitle('');
            setDescription('');
            setDueDate();
            setStatus('');
            setPriority('');
            alert('Task created successfully!');
        } catch (error) {
            console.error('Error creating task:', error);
            setError('Failed to create task. Please try again.');
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
            <h2 className="create_task_title">Create Task</h2>
            {error && <div style={{ color: 'red' }}>{error}</div>}
            <form className="form" onSubmit={createTask}>
                <input 
                    className="form_title"
                    type="text"
                    placeholder="Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                />
                <textarea
                    className="form_desc"
                    type="text"
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    required
                />
                <select
                    className="form_status"
                    value={status}
                    onChange={(e) => setStatus(e.target.value)}
                    required
                >
                <option value="">Select Status</option>
                    <option value="PENDING">Pending</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="COMPLETED">Completed</option>
                </select>
                <select
                    className="form_priority"
                    value={priority}
                    onChange={(e) => setPriority(e.target.value)} 
                    
                    required
                >
                    <option value="">Select Priority</option>
                    <option value="LOW">Low</option>
                    <option value="MEDIUM">Medium</option>
                    <option value="HIGH">High</option>
                </select>
                <button className="form_submit" type="submit">Create</button>
            </form>
        </div>
    );
};

export default CreateTask;
