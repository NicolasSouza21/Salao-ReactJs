// Local do arquivo: src/main/java/com/seusalao/sistemadesalao/dto/AgendamentoRequest.java

package com.seusalao.sistemadesalao.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ✅ NOVO DTO: AgendamentoRequest
 * Classe para transportar os dados da requisição do frontend para criar um agendamento.
 */
@Data
public class AgendamentoRequest {
    private Long clienteId;
    private List<Long> servicoIds;
    private LocalDateTime dataHora;
    private String observacoes;
}