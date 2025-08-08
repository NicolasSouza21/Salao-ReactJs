// Local do arquivo: src/pages/AtendimentosPage.jsx

import React, { useState, useEffect } from 'react';
import { 
    createAtendimento, 
    getAllAtendimentos, 
    getAllClientes, 
    getAllServicos 
} from '../services/apiService.js';
// ✨ ALTERAÇÃO AQUI: Importamos o nosso novo e elaborado arquivo CSS.
import './AtendimentosPage.css'; 

function AtendimentosPage() {
    // Listas de dados
    const [atendimentos, setAtendimentos] = useState([]);
    const [clientes, setClientes] = useState([]);
    const [servicos, setServicos] = useState([]);

    // Estado do formulário
    const [selectedCliente, setSelectedCliente] = useState('');
    const [selectedServicos, setSelectedServicos] = useState([]); 
    const [observacoes, setObservacoes] = useState('');
    
    // Controle de UI
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [formError, setFormError] = useState('');
    const [formSuccess, setFormSuccess] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [clientesRes, servicosRes, atendimentosRes] = await Promise.all([
                    getAllClientes(),
                    getAllServicos(),
                    getAllAtendimentos()
                ]);
                setClientes(clientesRes.data);
                setServicos(servicosRes.data);
                setAtendimentos(atendimentosRes.data);
            } catch (err) {
                setError('Falha ao carregar dados. Verifique a API e tente novamente.');
                console.error(err);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, []);
    
    const handleServicoChange = (event) => {
        const { options } = event.target;
        const selectedIds = [];
        for (const option of options) {
            if (option.selected) {
                selectedIds.push(option.value);
            }
        }
        setSelectedServicos(selectedIds);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setFormError('');
        setFormSuccess('');

        if (!selectedCliente || selectedServicos.length === 0) {
            setFormError('Selecione um cliente e pelo menos um serviço.');
            return;
        }

        const atendimentoData = {
            clienteId: selectedCliente,
            servicoIds: selectedServicos, 
            observacoes: observacoes
        };

        try {
            const response = await createAtendimento(atendimentoData);
            setFormSuccess('Atendimento registrado com sucesso!');
            setAtendimentos(prev => [...prev, response.data]);
            
            setSelectedCliente('');
            setSelectedServicos([]); 
            setObservacoes('');

        } catch (err) {
            setFormError('Erro ao registrar atendimento.');
            console.error(err);
        }
    };

    if (loading) return <p>Carregando...</p>;
    if (error) return <div className="page-error">{error}</div>;

    return (
        <div className="page-container">
            <h1 className="page-title">Registro de Atendimentos</h1>

            <div className="module-container">
                <form onSubmit={handleSubmit} className="form-module">
                    <h2>Novo Atendimento</h2>
                    {formError && <p className="form-error">{formError}</p>}
                    {formSuccess && <p className="form-success">{formSuccess}</p>}

                    <div className="form-group">
                        <h3>Cliente</h3>
                        <select value={selectedCliente} onChange={(e) => setSelectedCliente(e.target.value)} required>
                            <option value="">-- Selecione um Cliente --</option>
                            {clientes.map(cliente => (
                                <option key={cliente.id} value={cliente.id}>{cliente.nome}</option>
                            ))}
                        </select>
                    </div>

                    <div className="form-group">
                        <h3>Serviços Realizados (segure Ctrl/Cmd para selecionar vários)</h3>
                        <select 
                            multiple={true} 
                            value={selectedServicos} 
                            onChange={handleServicoChange}
                        >
                            {servicos.map(servico => (
                                <option key={servico.id} value={servico.id}>
                                    {servico.nome} ({new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(servico.valor)})
                                </option>
                            ))}
                        </select>
                    </div>
                    
                    <div className="form-group">
                        <h3>Observações</h3>
                        <textarea value={observacoes} onChange={(e) => setObservacoes(e.target.value)} placeholder="Detalhes do atendimento, produtos usados, etc." />
                    </div>

                    <button type="submit">Registrar Atendimento</button>
                </form>
            </div>

            <div className="module-container">
                <h2>Histórico de Atendimentos</h2>
                <table className="data-table">
                    <thead>
                        <tr>
                            <th>Data</th>
                            <th>Cliente</th>
                            <th>Serviços</th>
                            <th>Valor Total</th>
                            <th>Observações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {atendimentos.length > 0 ? (
                            atendimentos.map(atendimento => (
                                <tr key={atendimento.id}>
                                    <td>{new Date(atendimento.dataAtendimento).toLocaleDateString('pt-BR', {timeZone: 'UTC'})}</td>
                                    <td>{atendimento.cliente?.nome || 'Cliente não encontrado'}</td>
                                    <td>{atendimento.servicos.map(s => s.nome).join(', ')}</td>
                                    <td>{new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(atendimento.valorTotal)}</td>
                                    <td>{atendimento.observacoes}</td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="5">Nenhum atendimento registrado.</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default AtendimentosPage;