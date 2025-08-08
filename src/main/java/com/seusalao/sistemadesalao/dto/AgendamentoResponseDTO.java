package com.seusalao.sistemadesalao.dto;

import com.seusalao.sistemadesalao.model.Agendamento;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class AgendamentoResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private Agendamento.StatusAgendamento status;
    private String observacoes;
    private ClienteDTO cliente;
    private Set<ServicoDTO> servicos;

    // Construtor que converte uma Entidade em DTO
    public AgendamentoResponseDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.dataHora = agendamento.getDataHora();
        this.status = agendamento.getStatus();
        this.observacoes = agendamento.getObservacoes();

        // Mapeia o cliente para ClienteDTO
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setId(agendamento.getCliente().getId());
        clienteDto.setNome(agendamento.getCliente().getNome());
        this.cliente = clienteDto;

        // Mapeia o Set<Servico> para Set<ServicoDTO>
        this.servicos = agendamento.getServicos().stream().map(servico -> {
            ServicoDTO servicoDto = new ServicoDTO();
            servicoDto.setId(servico.getId());
            servicoDto.setNome(servico.getNome());
            servicoDto.setValor(servico.getValor());
            return servicoDto;
        }).collect(Collectors.toSet());
    }
}