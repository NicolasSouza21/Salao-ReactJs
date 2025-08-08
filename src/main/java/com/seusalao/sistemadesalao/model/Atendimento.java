// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/model/Atendimento.java

package com.seusalao.sistemadesalao.model;

// ✅ CORREÇÃO AQUI: Removemos a importação desnecessária.
// import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * ✅ NOVA ENTIDADE: ATENDIMENTO
 * Representa um registro de serviços prestados a um cliente em uma data específica.
 */
@Entity
@Table(name = "atendimentos")
@Data
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- RELACIONAMENTOS ---

    // ✅ CORREÇÃO AQUI: Removemos a anotação @JsonBackReference.
    // O sistema de DTOs agora controla a serialização, tornando esta anotação obsoleta.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "atendimento_servicos",
        joinColumns = @JoinColumn(name = "atendimento_id"),
        inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private Set<Servico> servicos = new HashSet<>();

    // --- CAMPOS PRÓPRIOS DO ATENDIMENTO ---

    @Column(name = "data_atendimento", nullable = false)
    private LocalDate dataAtendimento;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}