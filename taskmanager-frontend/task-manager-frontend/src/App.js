import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import TaskList from './components/TaskList';
import CreateTask from './components/CreateTask';
import SearchTaskById from './components/SearchTaskById';


function App() {
    return (
        <Router>
            <div>
                <Routes>
                    <Route path="/" element={<TaskList />} />
                    <Route path="/create" element={<CreateTask />} />
                    <Route path="/search" element={<SearchTaskById />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;