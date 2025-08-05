// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/service/ProdutoService.java

package com.seusalao.sistemadesalao.service;

import com.seusalao.sistemadesalao.model.Produto;
import com.seusalao.sistemadesalao.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ✅ CAMADA DE SERVIÇO PARA PRODUTO
 * Contém a lógica de negócio para as operações com produtos.
 */
@Service // ✨ ALTERAÇÃO AQUI: Marca esta classe como um Service (serviço) do Spring.
@RequiredArgsConstructor // ✨ ALTERAÇÃO AQUI: Lombok cria um construtor para injeção de dependência.
public class ProdutoService {

    // ✨ ALTERAÇÃO AQUI: Injeção de dependência do nosso repositório de produtos.
    private final ProdutoRepository produtoRepository;

    /**
     * Busca todos os produtos cadastrados.
     * @return Uma lista de todos os produtos.
     */
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    /**
     * Busca um produto específico pelo seu ID.
     * @param id O ID do produto.
     * @return Um Optional contendo o produto, se encontrado.
     */
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    /**
     * Salva um novo produto no banco de dados.
     * @param produto O objeto produto a ser salvo.
     * @return O produto salvo (com o ID preenchido).
     */
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    /**
     * Deleta um produto pelo seu ID.
     * @param id O ID do produto a ser deletado.
     */
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    /**
     * Atualiza um produto existente.
     * @param id O ID do produto a ser atualizado.
     * @param produtoAtualizado O objeto com as novas informações.
     * @return O produto atualizado.
     */
    public Produto update(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id)
            .map(produtoExistente -> {
                produtoExistente.setNome(produtoAtualizado.getNome());
                produtoExistente.setDescricao(produtoAtualizado.getDescricao());
                produtoExistente.setValor(produtoAtualizado.getValor());
                produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
                return produtoRepository.save(produtoExistente);
            }).orElseThrow(() -> new RuntimeException("Produto não encontrado com o id " + id));
    }
}