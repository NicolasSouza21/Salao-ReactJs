// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/controller/AgendamentoController.java

package com.seusalao.sistemadesalao.controller;

import com.seusalao.sistemadesalao.dto.AgendamentoRequest;
import com.seusalao.sistemadesalao.dto.AgendamentoResponseDTO; // ✨ ALTERAÇÃO AQUI
import com.seusalao.sistemadesalao.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ✅ NOVO CONTROLLER: AgendamentoController
 * Expõe os endpoints da API REST para as operações com Agendamentos.
 */
@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    /**
     * Endpoint para CRIAR um novo agendamento.
     * @return O agendamento criado (como DTO) com status 201.
     */
    // ✅ CORREÇÃO AQUI: O tipo de retorno agora é o nosso DTO.
    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> createAgendamento(@RequestBody AgendamentoRequest request) {
        AgendamentoResponseDTO novoAgendamentoDTO = agendamentoService.createAgendamento(
            request.getClienteId(),
            request.getServicoIds(),
            request.getDataHora(),
            request.getObservacoes()
        );
        return new ResponseEntity<>(novoAgendamentoDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint para LER todos os agendamentos.
     * @return Uma lista com todos os agendamentos (como DTOs).
     */
    // ✅ CORREÇÃO AQUI: O tipo de retorno agora é uma lista do nosso DTO.
    @GetMapping
    public List<AgendamentoResponseDTO> getAllAgendamentos() {
        return agendamentoService.findAll();
    }
}