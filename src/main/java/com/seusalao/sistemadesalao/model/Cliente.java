// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/model/Cliente.java

package com.seusalao.sistemadesalao.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // ✨ ALTERAÇÃO AQUI: Importamos a anotação correta.
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

/**
 * ✅ ENTIDADE CLIENTE
 * Representa um cliente do salão no banco de dados.
 */
@Entity 
@Table(name = "clientes") 
@Data 
public class Cliente {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false, length = 100) 
    private String nome;

    @Column(unique = true, length = 20) 
    private String telefone;

    @Column(name = "data_nascimento") 
    private LocalDate dataNascimento;

    @Column(columnDefinition = "TEXT") 
    private String observacoes;
    
    // ✅ CORREÇÃO AQUI: Adicionamos @JsonIgnore para quebrar o loop.
    // Isso diz à API: "Ao buscar a lista de clientes, não inclua a lista de atendimentos de cada um".
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Atendimento> atendimentos;

    // ✅ CORREÇÃO AQUI: Adicionamos @JsonIgnore aqui também pelo mesmo motivo.
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Agendamento> agendamentos;
}