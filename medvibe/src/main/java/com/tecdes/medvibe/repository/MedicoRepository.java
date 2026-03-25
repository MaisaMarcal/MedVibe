package com.tecdes.medvibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecdes.medvibe.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
