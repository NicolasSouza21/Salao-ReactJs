// Local do arquivo: src/pages/ServicosPage.jsx

import React, { useState, useEffect } from 'react';
import { getAllServicos, createServico } from '../../services/apiService.js'; // Usaremos as funções que já existem no apiService
// Usaremos o mesmo CSS da página de clientes para manter a consistência
import './ClientesPage.css'; 

function ServicosPage() {
    const [servicos, setServicos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Estados do formulário
    const [nome, setNome] = useState('');
    const [descricao, setDescricao] = useState('');
    const [valor, setValor] = useState('');
    const [formError, setFormError] = useState('');
    const [formSuccess, setFormSuccess] = useState('');

    const fetchServicos = async () => {
        try {
            setLoading(true);
            const response = await getAllServicos();
            setServicos(response.data);
        } catch (err) {
            setError('Não foi possível carregar os serviços.');
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchServicos();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setFormError('');
        setFormSuccess('');

        const novoServico = { nome, descricao, valor: parseFloat(valor) };

        try {
            const response = await createServico(novoServico);
            setFormSuccess(`Serviço "${response.data.nome}" cadastrado com sucesso!`);
            
            // Limpa o formulário e atualiza a lista
            setNome('');
            setDescricao('');
            setValor('');
            setServicos(listaAtual => [...listaAtual, response.data]);

        } catch (err) {
            setFormError('Erro ao cadastrar serviço. Verifique os dados.');
            console.error(err);
        }
    };

    if (error) return <div className="page-error">{error}</div>;

    return (
        <div className="page-container">
            <h1 className="page-title">Gestão de Serviços</h1>
            
            <div className="module-container">
                <form onSubmit={handleSubmit} className="form-module">
                    <h2>Cadastrar Novo Serviço</h2>
                    {formError && <p className="form-error">{formError}</p>}
                    {formSuccess && <p className="form-success">{formSuccess}</p>}
                    
                    <div className="form-row">
                        <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Nome do Serviço" required />
                        <input type="number" step="0.01" value={valor} onChange={(e) => setValor(e.target.value)} placeholder="Valor (ex: 50.00)" required />
                    </div>
                    <div className="form-row">
                        <textarea value={descricao} onChange={(e) => setDescricao(e.target.value)} placeholder="Descrição do Serviço (opcional)" />
                    </div>
                    <button type="submit">Salvar Serviço</button>
                </form>
            </div>

            <div className="module-container">
                <h2>Serviços Cadastrados</h2>
                {loading ? (
                    <p>Carregando...</p>
                ) : (
                    <table className="data-table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Descrição</th>
                                <th>Valor</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            {servicos.length === 0 ? (
                                <tr>
                                    <td colSpan="4">Nenhum serviço cadastrado.</td>
                                </tr>
                            ) : (
                                servicos.map(servico => (
                                    <tr key={servico.id}>
                                        <td>{servico.nome}</td>
                                        <td>{servico.descricao}</td>
                                        <td>{new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(servico.valor)}</td>
                                        <td>{/* Botões de Ações Futuras */}</td>
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

export default ServicosPage;