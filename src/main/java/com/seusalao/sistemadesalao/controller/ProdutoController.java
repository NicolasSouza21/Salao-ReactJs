// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/controller/ProdutoController.java

package com.seusalao.sistemadesalao.controller;

import com.seusalao.sistemadesalao.model.Produto;
import com.seusalao.sistemadesalao.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ✅ CONTROLLER PARA PRODUTOS
 * Expõe os endpoints da API REST para as operações de CRUD de Produtos.
 */
@RestController // ✨ ALTERAÇÃO AQUI: Define a classe como um controller REST.
@RequestMapping("/api/produtos") // ✨ ALTERAÇÃO AQUI: URL base para todos os endpoints de produtos.
@RequiredArgsConstructor // ✨ ALTERAÇÃO AQUI: Lombok para injeção de dependência.
@CrossOrigin(origins = "http://localhost:5173") // ✨ ALTERAÇÃO AQUI: Permite requisições do frontend React.
public class ProdutoController {

    private final ProdutoService produtoService;

    // Endpoint para CRIAR um novo produto (POST /api/produtos)
    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.save(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    // Endpoint para LER todos os produtos (GET /api/produtos)
    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.findAll();
    }

    // Endpoint para LER um produto por ID (GET /api/produtos/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para ATUALIZAR um produto (PUT /api/produtos/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails) {
        try {
            Produto produtoAtualizado = produtoService.update(id, produtoDetails);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para DELETAR um produto (DELETE /api/produtos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if (produtoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}