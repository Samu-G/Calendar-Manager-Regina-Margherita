import React from 'react';
import ReactDOM from 'react-dom/client';
import {
    BrowserRouter,
    Routes,
    Route,
} from "react-router-dom";

import './index.css';

import Login from "./Login";
import Registration from "./Registration";
import App from "./App";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/registrati" element={<Registration/>}/>
            <Route path="/app" element={<App/>}/>
        </Routes>
    </BrowserRouter>
);
