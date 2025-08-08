// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/repository/AgendamentoRepository.java

package com.seusalao.sistemadesalao.repository;

import com.seusalao.sistemadesalao.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ✅ NOVO REPOSITÓRIO: AgendamentoRepository
 * Interface para acessar os dados dos agendamentos no banco.
 */
@Repository // Define esta interface como um componente de repositório do Spring.
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // ✨ ALTERAÇÃO AQUI: Adicionamos um método de consulta customizado.
    // O Spring Data JPA criará automaticamente a consulta SQL para nós
    // baseada no nome do método. Este método encontrará todos os agendamentos
    // que ocorrem entre duas datas/horas específicas.
    List<Agendamento> findByDataHoraBetween(LocalDateTime start, LocalDateTime end);

}