// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/service/ClienteService.java

package com.seusalao.sistemadesalao.service;

import com.seusalao.sistemadesalao.model.Cliente;
import com.seusalao.sistemadesalao.repository.ClienteRepository;
import lombok.RequiredArgsConstructor; // ✨ ALTERAÇÃO AQUI: Import necessário para a anotação.
import org.springframework.stereotype.Service; // ✨ ALTERAÇÃO AQUI: Import necessário para a anotação.

import java.util.List;
import java.util.Optional;

/**
 * ✅ CAMADA DE SERVIÇO PARA CLIENTE
 * Contém a lógica de negócio para as operações com clientes.
 */
@Service // ✨ ALTERAÇÃO AQUI: Marca esta classe como um Service (serviço) do Spring.
@RequiredArgsConstructor // ✨ ALTERAÇÃO AQUI: Lombok cria um construtor com os campos 'final'.
public class ClienteService {

    // ✨ ALTERAÇÃO AQUI: Injeção de dependência do nosso repositório.
    // O 'final' garante que ele seja inicializado pelo construtor.
    private final ClienteRepository clienteRepository;

    /**
     * Busca todos os clientes cadastrados.
     * @return Uma lista de todos os clientes.
     */
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    /**
     * Busca um cliente específico pelo seu ID.
     * @param id O ID do cliente.
     * @return Um Optional contendo o cliente, se encontrado.
     */
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * Salva um novo cliente no banco de dados.
     * @param cliente O objeto cliente a ser salvo.
     * @return O cliente salvo (com o ID preenchido).
     */
    public Cliente save(Cliente cliente) {
        // Exemplo de regra de negócio: Poderíamos verificar se o telefone já existe antes de salvar.
        // Faremos isso no Controller para dar uma resposta HTTP mais clara.
        return clienteRepository.save(cliente);
    }

    /**
     * Deleta um cliente pelo seu ID.
     * @param id O ID do cliente a ser deletado.
     */
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    /**
     * Atualiza um cliente existente.
     * @param id O ID do cliente a ser atualizado.
     * @param clienteAtualizado O objeto com as novas informações.
     * @return O cliente atualizado.
     */
    public Cliente update(Long id, Cliente clienteAtualizado) {
        // Busca o cliente existente ou lança uma exceção se não encontrar.
        return clienteRepository.findById(id)
            .map(clienteExistente -> {
                clienteExistente.setNome(clienteAtualizado.getNome());
                clienteExistente.setTelefone(clienteAtualizado.getTelefone());
                clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());
                clienteExistente.setObservacoes(clienteAtualizado.getObservacoes());
                return clienteRepository.save(clienteExistente);
            }).orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id " + id));
    }
}