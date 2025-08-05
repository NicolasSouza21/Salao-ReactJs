// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/controller/ServicoController.java

package com.seusalao.sistemadesalao.controller;

import com.seusalao.sistemadesalao.model.Servico;
import com.seusalao.sistemadesalao.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ✅ CONTROLLER PARA SERVIÇOS
 * Expõe os endpoints da API REST para as operações de CRUD de Serviços.
 */
@RestController // ✨ ALTERAÇÃO AQUI: Define a classe como um controller REST.
@RequestMapping("/api/servicos") // ✨ ALTERAÇÃO AQUI: URL base para todos os endpoints de serviços.
@RequiredArgsConstructor // ✨ ALTERAÇÃO AQUI: Lombok para injeção de dependência.
@CrossOrigin(origins = "http://localhost:5173") // ✨ ALTERAÇÃO AQUI: Permite requisições do frontend React.
public class ServicoController {

    private final ServicoService servicoService;

    // Endpoint para CRIAR um novo serviço (POST /api/servicos)
    @PostMapping
    public ResponseEntity<Servico> createServico(@RequestBody Servico servico) {
        Servico novoServico = servicoService.save(servico);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    // Endpoint para LER todos os serviços (GET /api/servicos)
    @GetMapping
    public List<Servico> getAllServicos() {
        return servicoService.findAll();
    }

    // Endpoint para LER um serviço por ID (GET /api/servicos/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServicoById(@PathVariable Long id) {
        return servicoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para ATUALIZAR um serviço (PUT /api/servicos/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Servico> updateServico(@PathVariable Long id, @RequestBody Servico servicoDetails) {
        try {
            Servico servicoAtualizado = servicoService.update(id, servicoDetails);
            return ResponseEntity.ok(servicoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para DELETAR um serviço (DELETE /api/servicos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) {
        if (servicoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        servicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}