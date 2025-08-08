// Local do arquivo: src/App.jsx

import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar.jsx';
import DashboardPage from './pages/DashboardPage.jsx';
import ClientesPage from './pages/ClientesPage.jsx';
import ServicosPage from './pages/ServicosPage.jsx'; 
import AtendimentosPage from './pages/AtendimentosPage.jsx';
// ✨ ALTERAÇÃO AQUI: Importamos a nova página de agendamentos.
import AgendamentosPage from './pages/AgendamentosPage.jsx';
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
          <Route path="/atendimentos" element={<AtendimentosPage />} />
          {/* ✅ CORREÇÃO AQUI: Adicionamos a rota para a nova página de agenda. */}
          <Route path="/agenda" element={<AgendamentosPage />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;