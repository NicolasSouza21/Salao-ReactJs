// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/repository/ServicoRepository.java

package com.seusalao.sistemadesalao.repository;

import com.seusalao.sistemadesalao.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ✅ REPOSITÓRIO SERVIÇO
 * Interface para acessar os dados dos serviços no banco.
 */
@Repository // ✨ ALTERAÇÃO AQUI: Anotação que define esta interface como um repositório Spring.
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    // ✨ ALTERAÇÃO AQUI: Estendemos JpaRepository para ganhar todos os métodos de CRUD (save, findById, findAll, deleteById, etc.)
    // para a entidade Servico.

    // Por enquanto, não precisamos de nenhuma consulta customizada aqui.
    // Se no futuro precisarmos buscar serviços por nome, por exemplo,
    // poderíamos adicionar: Optional<Servico> findByNome(String nome);
}