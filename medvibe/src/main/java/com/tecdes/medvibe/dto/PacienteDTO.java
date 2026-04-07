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
    
    // RELACIONAMENTO: Lista de consultas simplificadas do paciente
    // Isso cumpre o requisito de demonstrar o mapeamento 1:N no DTO
    List<ConsultaDTO> historicoConsultas 
) {
}