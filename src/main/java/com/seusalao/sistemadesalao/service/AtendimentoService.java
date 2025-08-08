// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/service/AtendimentoService.java

package com.seusalao.sistemadesalao.service;

import com.seusalao.sistemadesalao.model.Atendimento;
import com.seusalao.sistemadesalao.model.Cliente;
import com.seusalao.sistemadesalao.model.Servico;
import com.seusalao.sistemadesalao.repository.AtendimentoRepository;
import com.seusalao.sistemadesalao.repository.ClienteRepository;
import com.seusalao.sistemadesalao.repository.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ✅ NOVO SERVICE: AtendimentoService
 * Contém a lógica de negócio para as operações com Atendimentos.
 */
@Service
@RequiredArgsConstructor
public class AtendimentoService {

    // ✨ ALTERAÇÃO AQUI: Injetamos todos os repositórios necessários.
    private final AtendimentoRepository atendimentoRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    /**
     * Busca todos os atendimentos cadastrados.
     * @return Uma lista de todos os atendimentos.
     */
    public List<Atendimento> findAll() {
        return atendimentoRepository.findAll();
    }

    /**
     * Cria um novo atendimento.
     * @param clienteId O ID do cliente.
     * @param servicoIds A lista de IDs dos serviços realizados.
     * @param observacoes Observações adicionais sobre o atendimento.
     * @return O atendimento criado e salvo no banco.
     */
    public Atendimento createAtendimento(Long clienteId, List<Long> servicoIds, String observacoes) {
        // 1. Busca o cliente no banco de dados.
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o id: " + clienteId));

        // 2. Busca todos os serviços selecionados.
        List<Servico> servicosRealizados = servicoRepository.findAllById(servicoIds);
        if (servicosRealizados.isEmpty()) {
            throw new IllegalArgumentException("A lista de serviços não pode ser vazia.");
        }

        // 3. Calcula o valor total somando o valor de cada serviço.
        BigDecimal valorTotal = servicosRealizados.stream()
                .map(Servico::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. Cria a nova instância do Atendimento.
        Atendimento novoAtendimento = new Atendimento();
        novoAtendimento.setCliente(cliente);
        novoAtendimento.setServicos(new HashSet<>(servicosRealizados)); // Converte a lista para Set
        novoAtendimento.setDataAtendimento(LocalDate.now()); // Usa a data atual
        novoAtendimento.setValorTotal(valorTotal);
        novoAtendimento.setObservacoes(observacoes);

        // 5. Salva o novo atendimento no banco.
        return atendimentoRepository.save(novoAtendimento);
    }
}