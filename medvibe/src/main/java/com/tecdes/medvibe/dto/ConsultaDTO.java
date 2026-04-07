package com.tecdes.medvibe.dto;

import lombok.Builder;

@Builder
public record ConsultaDTO(Long id, String dataHora, Long pacienteId, Long medicoId, String nomePaciente, String nomeMedico) {
    }
