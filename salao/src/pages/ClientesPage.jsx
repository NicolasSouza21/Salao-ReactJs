// Local do arquivo: src/pages/ClientesPage.jsx

import React, { useState, useEffect } from 'react';
import { getAllClientes, createCliente } from '../../services/apiService.js';
import './ClientesPage.css'; // Criaremos este CSS a seguir

// Componente para o formulário (dentro do mesmo arquivo para simplificar)
const ClienteForm = ({ onClienteAdicionado }) => {
    const [nome, setNome] = useState('');
    const [telefone, setTelefone] = useState('');
    const [dataNascimento, setDataNascimento] = useState('');
    const [observacoes, setObservacoes] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');

        const novoCliente = { nome, telefone, dataNascimento, observacoes };

        try {
            const response = await createCliente(novoCliente);
            setSuccess(`Cliente "${response.data.nome}" cadastrado com sucesso!`);
            setNome('');
            setTelefone('');
            setDataNascimento('');
            setObservacoes('');
            onClienteAdicionado(response.data);
        } catch (err) {
            setError('Erro ao cadastrar cliente. Verifique os dados.');
            console.error(err);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="form-module">
            <h2>Cadastrar Novo Cliente</h2>
            {error && <p className="form-error">{error}</p>}
            {success && <p className="form-success">{success}</p>}
            
            <div className="form-row">
                <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Nome do Cliente" required />
                <input type="text" value={telefone} onChange={(e) => setTelefone(e.target.value)} placeholder="Telefone" required />
            </div>
            <div className="form-row">
                <input type="date" value={dataNascimento} onChange={(e) => setDataNascimento(e.target.value)} title="Data de Nascimento" />
                <textarea value={observacoes} onChange={(e) => setObservacoes(e.target.value)} placeholder="Observações" />
            </div>
            <button type="submit">Salvar Cliente</button>
        </form>
    );
};

// Componente principal da página
function ClientesPage() {
    const [clientes, setClientes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchClientes = async () => {
        try {
            setLoading(true);
            const response = await getAllClientes();
            setClientes(response.data);
        } catch (err) {
            setError('Não foi possível carregar os clientes.');
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchClientes();
    }, []);

    const handleClienteAdicionado = (novoCliente) => {
        // Adiciona o novo cliente à lista sem precisar buscar tudo de novo.
        setClientes(listaAtual => [...listaAtual, novoCliente]);
    };

    if (error) return <div className="page-error">{error}</div>;

    return (
        <div className="page-container">
            <h1 className="page-title">Gestão de Clientes</h1>
            <div className="module-container">
                <ClienteForm onClienteAdicionado={handleClienteAdicionado} />
            </div>
            <div className="module-container">
                <h2>Clientes Cadastrados</h2>
                {loading ? (
                    <p>Carregando...</p>
                ) : (
                    <table className="data-table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Telefone</th>
                                <th>Data de Nascimento</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            {clientes.length === 0 ? (
                                <tr>
                                    <td colSpan="4">Nenhum cliente cadastrado.</td>
                                </tr>
                            ) : (
                                clientes.map(cliente => (
                                    <tr key={cliente.id}>
                                        <td>{cliente.nome}</td>
                                        <td>{cliente.telefone}</td>
                                        <td>{cliente.dataNascimento ? new Date(cliente.dataNascimento).toLocaleDateString() : 'N/A'}</td>
                                        <td>{/* Botões de Editar/Excluir no futuro */}</td>
                                    </tr>
                                ))
                            )}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
}

export default ClientesPage;