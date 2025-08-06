// Local do arquivo: src/components/ClienteList.jsx

import React, { useState, useEffect } from 'react';
// ✅ CORREÇÃO AQUI: O nome do arquivo 'apiService' agora está com 'a' minúsculo, exatamente como o nome do arquivo.
import { getAllClientes } from '../services/apiService';

function ClienteList() {
    const [clientes, setClientes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchClientes = async () => {
            try {
                const response = await getAllClientes();
                setClientes(response.data);
            } catch (err) {
                setError('Não foi possível carregar os clientes.');
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchClientes();
    }, []);

    if (loading) return <div>Carregando clientes...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div>
            <h1>Lista de Clientes</h1>
            {clientes.length === 0 ? (
                <p>Nenhum cliente cadastrado.</p>
            ) : (
                <ul>
                    {clientes.map(cliente => (
                        <li key={cliente.id}>
                            <strong>{cliente.nome}</strong> - {cliente.telefone}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default ClienteList;