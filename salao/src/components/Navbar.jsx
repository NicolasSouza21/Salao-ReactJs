// Local do arquivo: src/components/Navbar.jsx

import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-brand">
        <Link to="/">Salão da Cassiana</Link>
      </div>
      <ul className="navbar-links">
        <li>
          <Link to="/">Dashboard</Link>
        </li>
        <li>
          <Link to="/clientes">Clientes</Link>
        </li>
        <li>
          <Link to="/servicos">Serviços</Link>
        </li>
        {/* ✨ ALTERAÇÃO AQUI: Adicionamos o link para a nova página de Atendimentos. */}
        <li>
          <Link to="/atendimentos">Atendimentos</Link>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;