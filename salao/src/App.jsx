// Local do arquivo: src/App.jsx

import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar.jsx';
import DashboardPage from './pages/DashboardPage.jsx';
import ClientesPage from './pages/ClientesPage.jsx';
import ServicosPage from './pages/ServicosPage.jsx'; 
// ✨ ALTERAÇÃO AQUI: Importamos a nova página de atendimentos.
import AtendimentosPage from './pages/AtendimentosPage.jsx';
import './App.css';

function App() {
  return (
    <div>
      <Navbar />
      
      <div className="content-container">
        <Routes>
          <Route path="/" element={<DashboardPage />} />
          <Route path="/clientes" element={<ClientesPage />} />
          <Route path="/servicos" element={<ServicosPage />} />
          {/* ✅ CORREÇÃO AQUI: Adicionamos a rota para a página de atendimentos. */}
          <Route path="/atendimentos" element={<AtendimentosPage />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;