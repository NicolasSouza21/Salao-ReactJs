// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/service/ServicoService.java

package com.seusalao.sistemadesalao.service;

import com.seusalao.sistemadesalao.model.Servico;
import com.seusalao.sistemadesalao.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ✅ CAMADA DE SERVIÇO PARA SERVIÇO
 * Contém a lógica de negócio para as operações com serviços/procedimentos.
 */
@Service // ✨ ALTERAÇÃO AQUI: Marca esta classe como um Service (serviço) do Spring.
@RequiredArgsConstructor // ✨ ALTERAÇÃO AQUI: Lombok cria um construtor para injeção de dependência.
public class ServicoService {

    // ✨ ALTERAÇÃO AQUI: Injeção de dependência do nosso repositório de serviços.
    private final ServicoRepository servicoRepository;

    /**
     * Busca todos os serviços cadastrados.
     * @return Uma lista de todos os serviços.
     */
    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    /**
     * Busca um serviço específico pelo seu ID.
     * @param id O ID do serviço.
     * @return Um Optional contendo o serviço, se encontrado.
     */
    public Optional<Servico> findById(Long id) {
        return servicoRepository.findById(id);
    }

    /**
     * Salva um novo serviço no banco de dados.
     * @param servico O objeto servico a ser salvo.
     * @return O serviço salvo (com o ID preenchido).
     */
    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }

    /**
     * Deleta um serviço pelo seu ID.
     * @param id O ID do serviço a ser deletado.
     */
    public void deleteById(Long id) {
        servicoRepository.deleteById(id);
    }

    /**
     * Atualiza um serviço existente.
     * @param id O ID do serviço a ser atualizado.
     * @param servicoAtualizado O objeto com as novas informações.
     * @return O serviço atualizado.
     */
    public Servico update(Long id, Servico servicoAtualizado) {
        return servicoRepository.findById(id)
            .map(servicoExistente -> {
                servicoExistente.setNome(servicoAtualizado.getNome());
                servicoExistente.setDescricao(servicoAtualizado.getDescricao());
                servicoExistente.setValor(servicoAtualizado.getValor());
                return servicoRepository.save(servicoExistente);
            }).orElseThrow(() -> new RuntimeException("Serviço não encontrado com o id " + id));
    }
}