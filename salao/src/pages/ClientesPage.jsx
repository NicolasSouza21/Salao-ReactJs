// Local do arquivo: src/pages/ClientesPage.jsx

import React, { useState, useEffect } from 'react';
// ✅ CORREÇÃO AQUI: O caminho foi ajustado de '../../services/apiService.js' para o correto '../services/apiService.js'.
import { getAllClientes, createCliente, deleteCliente } from '../services/apiService.js';
import './ClientesPage.css';

// O componente de formulário não precisa de alterações.
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
        setClientes(listaAtual => [...listaAtual, novoCliente]);
    };

    const handleDeleteCliente = async (clienteId) => {
        if (window.confirm('Tem certeza que deseja excluir este cliente?')) {
            try {
                await deleteCliente(clienteId);
                setClientes(listaAtual => listaAtual.filter(cliente => cliente.id !== clienteId));
            } catch (err) {
                alert('Erro ao excluir cliente.');
                console.error(err);
            }
        }
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
                            {clientes.map(cliente => (
                                <tr key={cliente.id}>
                                    <td>{cliente.nome}</td>
                                    <td>{cliente.telefone}</td>
                                    <td>{cliente.dataNascimento ? new Date(cliente.dataNascimento).toLocaleDateString('pt-BR', {timeZone: 'UTC'}) : 'N/A'}</td>
                                    <td>
                                        <div className="actions-cell">
                                            <button className="btn-delete" onClick={() => handleDeleteCliente(cliente.id)}>
                                                Excluir
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
}

export default ClientesPage;