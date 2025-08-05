// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/repository/ClienteRepository.java

package com.seusalao.sistemadesalao.repository;

import com.seusalao.sistemadesalao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * ✅ REPOSITÓRIO CLIENTE
 * Interface para acessar os dados dos clientes no banco.
 * O Spring Data JPA implementará os métodos automaticamente.
 */
@Repository // ✨ ALTERAÇÃO AQUI: Indica ao Spring que esta é uma interface de repositório.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // ✨ ALTERAÇÃO AQUI: O Spring criará automaticamente uma consulta para buscar clientes pelo telefone.
    Optional<Cliente> findByTelefone(String telefone);

    // ✨ ALTERAÇÃO AQUI: O Spring criará uma consulta para encontrar todos os clientes que fazem aniversário em uma data específica.
    List<Cliente> findByDataNascimento(LocalDate dataNascimento);

}