// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/model/Atendimento.java

package com.seusalao.sistemadesalao.model;

// ✨ ALTERAÇÃO AQUI: Importamos a anotação que resolve o problema.
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    // ✅ CORREÇÃO AQUI: Adicionamos @JsonBackReference.
    // Esta anotação é o par da @JsonManagedReference que colocamos no Cliente.
    // Ela diz: "Ao converter este Atendimento para JSON, inclua os dados do Cliente,
    // mas não a lista de atendimentos que está dentro dele". Isso quebra o loop.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference // Esta é a linha que resolve o erro 500.
    private Cliente cliente;

    // A relação com Serviço não causa loop, então não precisa de anotações especiais.
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