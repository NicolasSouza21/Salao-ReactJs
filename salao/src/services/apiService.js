
import axios from 'axios';

// ✅ ALTERAÇÃO AQUI: Cria uma instância do Axios com a URL base da nossa API.
// Isso evita que a gente precise digitar 'http://localhost:8080' toda vez.
const apiClient = axios.create({
    baseURL: 'http://localhost:8081/api', // Se você mudou a porta, ajuste aqui.
    headers: {
        'Content-Type': 'application/json'
    }
});

// ======================================================
// ✨ FUNÇÕES PARA CLIENTES ✨
// ======================================================
export const getAllClientes = () => apiClient.get('/clientes');
export const getClienteById = (id) => apiClient.get(`/clientes/${id}`);
export const createCliente = (clienteData) => apiClient.post('/clientes', clienteData);
export const updateCliente = (id, clienteData) => apiClient.put(`/clientes/${id}`, clienteData);
export const deleteCliente = (id) => apiClient.delete(`/clientes/${id}`);

// ======================================================
// ✨ FUNÇÕES PARA SERVIÇOS ✨
// ======================================================
export const getAllServicos = () => apiClient.get('/servicos');
// ... aqui podemos adicionar as outras funções para serviços no futuro ...

// ======================================================
// ✨ FUNÇÕES PARA PRODUTOS ✨
// ======================================================
export const getAllProdutos = () => apiClient.get('/produtos');
// ... e aqui as funções para produtos ...