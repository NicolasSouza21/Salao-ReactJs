// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/service/AgendamentoService.java

package com.seusalao.sistemadesalao.service;

import com.seusalao.sistemadesalao.dto.AgendamentoResponseDTO; // ✨ ALTERAÇÃO AQUI
import com.seusalao.sistemadesalao.model.Agendamento;
import com.seusalao.sistemadesalao.model.Cliente;
import com.seusalao.sistemadesalao.model.Servico;
import com.seusalao.sistemadesalao.repository.AgendamentoRepository;
import com.seusalao.sistemadesalao.repository.ClienteRepository;
import com.seusalao.sistemadesalao.repository.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors; // ✨ ALTERAÇÃO AQUI

/**
 * ✅ NOVO SERVICE: AgendamentoService
 * Contém a lógica de negócio para as operações com Agendamentos.
 */
@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    /**
     * Busca todos os agendamentos e os converte para o formato de resposta (DTO).
     * @return Uma lista de AgendamentoResponseDTO.
     */
    // ✅ CORREÇÃO AQUI: O método agora retorna uma lista do nosso DTO.
    public List<AgendamentoResponseDTO> findAll() {
        return agendamentoRepository.findAll()
                .stream()
                .map(AgendamentoResponseDTO::new) // Converte cada Agendamento para AgendamentoResponseDTO
                .collect(Collectors.toList());
    }

    /**
     * Cria um novo agendamento no sistema.
     * @return O agendamento salvo, convertido para DTO.
     */
    // ✅ CORREÇÃO AQUI: O método de criação também retorna o DTO.
    public AgendamentoResponseDTO createAgendamento(Long clienteId, List<Long> servicoIds, LocalDateTime dataHora, String observacoes) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o id: " + clienteId));

        List<Servico> servicosSelecionados = servicoRepository.findAllById(servicoIds);
        if (servicosSelecionados.isEmpty()) {
            throw new IllegalArgumentException("É necessário selecionar pelo menos um serviço para o agendamento.");
        }

        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setCliente(cliente);
        novoAgendamento.setServicos(new HashSet<>(servicosSelecionados));
        novoAgendamento.setDataHora(dataHora);
        novoAgendamento.setObservacoes(observacoes);
        novoAgendamento.setStatus(Agendamento.StatusAgendamento.AGENDADO);

        Agendamento agendamentoSalvo = agendamentoRepository.save(novoAgendamento);

        // Converte a entidade salva para o DTO antes de retornar.
        return new AgendamentoResponseDTO(agendamentoSalvo);
    }

    /**
     * Atualiza o status de um agendamento existente.
     * @return O agendamento atualizado, convertido para DTO.
     */
    // ✅ CORREÇÃO AQUI: O método de atualização também retorna o DTO.
    public AgendamentoResponseDTO updateStatus(Long id, Agendamento.StatusAgendamento novoStatus) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com o id: " + id));
        
        agendamento.setStatus(novoStatus);
        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
        return new AgendamentoResponseDTO(agendamentoSalvo);
    }
}