// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/model/Servico.java

package com.seusalao.sistemadesalao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;

/**
 * ✅ ENTIDADE SERVICO
 * Representa um procedimento ou serviço oferecido pelo salão.
 */
@Entity // ✨ ALTERAÇÃO AQUI: Marca esta classe como uma entidade.
@Table(name = "servicos") // ✨ ALTERAÇÃO AQUI: Define o nome da tabela como "servicos".
@Data // ✨ ALTERAÇÃO AQUI: Anotação do Lombok para gerar getters, setters, etc.
public class Servico {

    @Id // ✨ ALTERAÇÃO AQUI: Chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✨ ALTERAÇÃO AQUI: ID autoincrementado.
    private Long id;

    @Column(nullable = false, unique = true, length = 100) // ✨ ALTERAÇÃO AQUI: O nome do serviço não pode ser nulo e deve ser único.
    private String nome;

    @Column(columnDefinition = "TEXT") // ✨ ALTERAÇÃO AQUI: Campo para uma descrição mais detalhada do serviço.
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2) // ✨ ALTERAÇÃO AQUI: Campo para o valor do serviço.
    private BigDecimal valor;
}