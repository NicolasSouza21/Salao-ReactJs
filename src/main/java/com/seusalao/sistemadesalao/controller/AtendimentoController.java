// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/controller/AtendimentoController.java

package com.seusalao.sistemadesalao.controller;

import com.seusalao.sistemadesalao.dto.AtendimentoRequest;
import com.seusalao.sistemadesalao.model.Atendimento;
import com.seusalao.sistemadesalao.service.AtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ✅ NOVO CONTROLLER: AtendimentoController
 * Expõe os endpoints da API REST para as operações com Atendimentos.
 */
@RestController
@RequestMapping("/api/atendimentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // Permite requisições do frontend
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    /**
     * Endpoint para CRIAR um novo atendimento (POST /api/atendimentos)
     */
    @PostMapping
    public ResponseEntity<Atendimento> createAtendimento(@RequestBody AtendimentoRequest request) {
        Atendimento novoAtendimento = atendimentoService.createAtendimento(
            request.getClienteId(),
            request.getServicoIds(),
            request.getObservacoes()
        );
        return new ResponseEntity<>(novoAtendimento, HttpStatus.CREATED);
    }

    /**
     * Endpoint para LER todos os atendimentos (GET /api/atendimentos)
     */
    @GetMapping
    public List<Atendimento> getAllAtendimentos() {
        return atendimentoService.findAll();
    }
}