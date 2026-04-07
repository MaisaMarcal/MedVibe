package com.tecdes.medvibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tecdes.medvibe.model.Paciente;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Busca paciente pelo CPF para evitar cadastros duplicados
    Optional<Paciente> findByCpf(String cpf);

    // Verifica se um CPF já existe na base
    boolean existsByCpf(String cpf);
    
    // Busca paciente pelo nome exato (útil para filtros)
    Optional<Paciente> findByNomeContainingIgnoreCase(String nome);
}