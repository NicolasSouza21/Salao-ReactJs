// Local do arquivo: src/pages/DashboardPage.jsx

import React from 'react';
import ClienteList from '../components/ClienteList.jsx'; // Importa o componente de clientes
import './DashboardPage.css'; // Importaremos um CSS para estilizar a página

function DashboardPage() {
    return (
        <div className="dashboard">
            <header className="dashboard-header">
                <h1>Dashboard do Salão</h1>
                <p>Gerencie seus clientes, agendamentos e vendas em um só lugar.</p>
            </header>
            
            <main className="dashboard-main">
                {/* Este é o "widget" de gerenciamento de clientes */}
                <section className="dashboard-module">
                    <ClienteList />
                </section>

                {/* No futuro, outros módulos entrarão aqui, como "Agenda do Dia" ou "Caixa" */}
            </main>
        </div>
    );
}

export default DashboardPage;