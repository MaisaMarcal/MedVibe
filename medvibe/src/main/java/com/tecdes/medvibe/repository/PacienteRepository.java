package com.tecdes.medvibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecdes.medvibe.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
