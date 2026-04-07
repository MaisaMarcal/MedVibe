package com.tecdes.medvibe.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record PacienteDTO(
    Long id,
    String nome,
    String cpf,          // Alterado de Long para String (preservar zeros à esquerda)
    Integer idade,       // Alterado de String para Integer (facilita cálculos de triagem)
    String sintoma,
    List<ConsultaDTO>converterParaDTO
){}