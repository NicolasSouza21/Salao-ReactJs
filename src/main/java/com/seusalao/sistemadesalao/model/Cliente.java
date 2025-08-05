// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/model/Cliente.java

package com.seusalao.sistemadesalao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;

/**
 * ✅ ENTIDADE CLIENTE
 * Representa um cliente do salão no banco de dados.
 */
@Entity // ✨ ALTERAÇÃO AQUI: Informa ao JPA que esta classe é uma entidade do banco de dados.
@Table(name = "clientes") // ✨ ALTERAÇÃO AQUI: Define o nome da tabela no banco como "clientes".
@Data // ✨ ALTERAÇÃO AQUI: Anotação do Lombok para gerar getters, setters, toString, etc. automaticamente.
public class Cliente {

    @Id // ✨ ALTERAÇÃO AQUI: Marca este campo como a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✨ ALTERAÇÃO AQUI: Configura o ID para ser autoincrementado pelo banco de dados.
    private Long id;

    @Column(nullable = false, length = 100) // ✨ ALTERAÇÃO AQUI: Campo não pode ser nulo e tem tamanho máximo de 100 caracteres.
    private String nome;

    @Column(unique = true, length = 20) // ✨ ALTERAÇÃO AQUI: Telefone deve ser único, para evitar clientes duplicados.
    private String telefone;

    @Column(name = "data_nascimento") // ✨ ALTERAÇÃO AQUI: Define o nome da coluna e armazena a data de nascimento para os alertas.
    private LocalDate dataNascimento;

    @Column(columnDefinition = "TEXT") // ✨ ALTERAÇÃO AQUI: Campo de texto sem limite de tamanho para observações.
    private String observacoes;
}