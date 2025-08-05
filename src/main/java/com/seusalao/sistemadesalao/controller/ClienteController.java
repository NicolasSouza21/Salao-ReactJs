// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/controller/ClienteController.java

package com.seusalao.sistemadesalao.controller;

import com.seusalao.sistemadesalao.model.Cliente;
import com.seusalao.sistemadesalao.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ✅ CONTROLLER PARA CLIENTES
 * Expõe os endpoints da API REST para as operações de CRUD de Clientes.
 */
@RestController // ✨ ALTERAÇÃO AQUI: Combina @Controller e @ResponseBody, simplificando a criação de APIs REST.
@RequestMapping("/api/clientes") // ✨ ALTERAÇÃO AQUI: Mapeia todas as requisições que começam com /api/clientes para este controller.
@RequiredArgsConstructor // ✨ ALTERAÇÃO AQUI: Lombok para injeção de dependência do service.
@CrossOrigin(origins = "http://localhost:5173") // ✨ ALTERAÇÃO AQUI: Permite requisições do nosso frontend React.
public class ClienteController {

    private final ClienteService clienteService;

    // Endpoint para CRIAR um novo cliente (POST /api/clientes)
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.save(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED); // Retorna o status 201 Created
    }

    // Endpoint para LER todos os clientes (GET /api/clientes)
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    // Endpoint para LER um cliente por ID (GET /api/clientes/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com o cliente
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }

    // Endpoint para ATUALIZAR um cliente (PUT /api/clientes/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        try {
            Cliente clienteAtualizado = clienteService.update(id, clienteDetails);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para DELETAR um cliente (DELETE /api/clientes/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clienteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o cliente não existir
        }
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content, indicando sucesso na exclusão
    }
}