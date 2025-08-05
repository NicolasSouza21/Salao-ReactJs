// Local do arquivo: src/components/Navbar.jsx

import React from 'react';
import { Link } from 'react-router-dom'; // ✨ ALTERAÇÃO AQUI: Importa o componente Link para navegação.
import './Navbar.css'; // Importaremos um CSS para estilizar a navbar.

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-brand">
        {/* O Link funciona como uma tag <a>, mas para rotas internas */}
        <Link to="/">Salão da Cassiana</Link>
      </div>
      <ul className="navbar-links">
        <li>
          <Link to="/">Dashboard</Link>
        </li>
        <li>
          <Link to="/clientes">Clientes</Link>
        </li>
        {/* No futuro, adicionaremos links para Serviços, Produtos, Caixa, etc. */}
      </ul>
    </nav>
  );
}

export default Navbar;