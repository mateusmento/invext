import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Client from './Client';
import { RouterProvider, createBrowserRouter, redirect } from 'react-router-dom';
import { Attendant } from './Attendant';
import { Root } from './Root';

const router = createBrowserRouter([
    {
        path: '/',
        Component: Root,
        action: () => redirect('/client'),
        children: [
            {
                path: '/client',
                Component: Client                
            },
            {
                path: '/attendant',
                Component: Attendant
            }
        ]
    },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<RouterProvider router={router} />);
