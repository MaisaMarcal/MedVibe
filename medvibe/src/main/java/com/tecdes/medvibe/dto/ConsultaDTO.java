package com.tecdes.medvibe.dto;

import lombok.Builder;

@Builder
public record ConsultaDTO(
    Long id,
    String dataHora,
    // IDs para facilitar buscas específicas se necessário
    Long pacienteId,
    Long medicoId,
    // Nomes: Exigência clara da Etapa 2 para exibição no Postman
    String nomePaciente, 
    String nomeMedico,
    // Campo adicional útil para o contexto clínico
    String especialidadeMedico
) {
}