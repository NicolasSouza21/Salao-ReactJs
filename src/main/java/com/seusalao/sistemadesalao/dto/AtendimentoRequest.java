// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/dto/AtendimentoRequest.java

package com.seusalao.sistemadesalao.dto;

import lombok.Data;
import java.util.List;

/**
 * ✅ NOVO DTO: AtendimentoRequest
 * Classe simples para transportar os dados da requisição do frontend para o controller.
 */
@Data
public class AtendimentoRequest {
    private Long clienteId;
    private List<Long> servicoIds;
    private String observacoes;
}