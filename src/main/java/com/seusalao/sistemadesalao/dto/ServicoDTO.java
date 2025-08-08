package com.seusalao.sistemadesalao.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ServicoDTO {
    private Long id;
    private String nome;
    private BigDecimal valor;
}