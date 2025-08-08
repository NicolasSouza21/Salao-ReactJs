// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/model/Agendamento.java

package com.seusalao.sistemadesalao.model;

// ✅ CORREÇÃO AQUI: Removemos a importação desnecessária.
// import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * ✅ NOVA ENTIDADE: AGENDAMENTO
 * Representa um horário marcado por um cliente para realizar serviços.
 */
@Entity
@Table(name = "agendamentos")
@Data
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- RELACIONAMENTOS ---

    // ✅ CORREÇÃO AQUI: Removemos a anotação @JsonBackReference.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "agendamento_servicos",
        joinColumns = @JoinColumn(name = "agendamento_id"),
        inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private Set<Servico> servicos = new HashSet<>();


    // --- CAMPOS PRÓPRIOS DO AGENDAMENTO ---

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgendamento status;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    public enum StatusAgendamento {
        AGENDADO,
        CONFIRMADO,
        CONCLUIDO,
        CANCELADO
    }
}