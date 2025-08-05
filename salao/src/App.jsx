// Local do arquivo: src/App.jsx

import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar.jsx';
import DashboardPage from './pages/DashboardPage.jsx';
import ClientesPage from './pages/ClientesPage.jsx';
import './App.css';

function App() {
  return (
    <div>
      <Navbar />
      
      {/* ✅ CORREÇÃO AQUI: Adicionamos um div para envolver o conteúdo das páginas */}
      <div className="content-container">
        <Routes>
          <Route path="/" element={<DashboardPage />} />
          <Route path="/clientes" element={<ClientesPage />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;