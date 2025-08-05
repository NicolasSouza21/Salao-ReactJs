// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/model/Produto.java

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
 * ✅ ENTIDADE PRODUTO
 * Representa um item físico vendido no salão.
 */
@Entity // ✨ ALTERAÇÃO AQUI: Marca esta classe como uma entidade.
@Table(name = "produtos") // ✨ ALTERAÇÃO AQUI: Define o nome da tabela como "produtos".
@Data // ✨ ALTERAÇÃO AQUI: Anotação do Lombok para gerar getters, setters, etc.
public class Produto {

    @Id // ✨ ALTERAÇÃO AQUI: Chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✨ ALTERAÇÃO AQUI: ID autoincrementado.
    private Long id;

    @Column(nullable = false, unique = true, length = 150) // ✨ ALTERAÇÃO AQUI: Nome do produto é obrigatório e único.
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2) // ✨ ALTERAÇÃO AQUI: Valor de venda do produto. Usamos BigDecimal para precisão.
    private BigDecimal valor;

    @Column(name = "quantidade_estoque", nullable = false) // ✨ ALTERAÇÃO AQUI: Campo para controlar o estoque.
    private Integer quantidadeEstoque;
}