package com.tecdes.medvibe.dto;

import lombok.Builder;

@Builder // Permite criar o DTO de forma fluida no Service
public record MedicoDTO(
    Long id,
    String nome,
    String cpf,          // Alterado de Long para String (CPFs possuem zeros à esquerda)
    String crm,          // Alterado de Long para String
    String especialidade
) {
}