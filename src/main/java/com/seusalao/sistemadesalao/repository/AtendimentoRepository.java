// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/repository/AtendimentoRepository.java

package com.seusalao.sistemadesalao.repository;

import com.seusalao.sistemadesalao.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ✅ NOVO REPOSITÓRIO: AtendimentoRepository
 * Interface para acessar os dados dos atendimentos no banco.
 */
@Repository // ✨ ALTERAÇÃO AQUI: Indica ao Spring que é um componente de repositório.
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    // ✨ ALTERAÇÃO AQUI: Mudamos de 'class' para 'interface' e estendemos JpaRepository.
    // O Spring Data JPA já nos fornece todos os métodos básicos de CRUD (save, findById, findAll, etc.).
    // No futuro, se precisarmos de consultas mais específicas, como "buscar todos os atendimentos de um cliente",
    // poderemos declará-las aqui. Ex: List<Atendimento> findByClienteId(Long clienteId);

}