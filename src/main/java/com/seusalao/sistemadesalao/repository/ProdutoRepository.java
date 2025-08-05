// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/repository/ProdutoRepository.java

package com.seusalao.sistemadesalao.repository;

import com.seusalao.sistemadesalao.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ✅ REPOSITÓRIO PRODUTO
 * Interface para acessar os dados dos produtos no banco.
 */
@Repository // ✨ ALTERAÇÃO AQUI: Indica ao Spring que é um componente de repositório.
public interface ProdutoRepository extends JpaRepository<Produto, Long> { // ✨ ALTERAÇÃO AQUI: Mudamos de 'class' para 'interface' e estendemos JpaRepository.

    // Herdamos todos os métodos de CRUD (save, findById, findAll, etc.) para a entidade Produto.
    // Não precisamos de métodos customizados por enquanto.

}