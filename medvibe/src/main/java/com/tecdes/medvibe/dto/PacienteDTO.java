package com.tecdes.medvibe.dto;

import lombok.Builder;


@Builder
public record PacienteDTO(Long id, String nome, String cpf, Integer idade, String sintoma) {
        //TODO Auto-generated constructor stub
    }