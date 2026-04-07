package com.tecdes.medvibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tecdes.medvibe.model.Medico;
import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Busca um médico pelo CRM (útil para evitar cadastros duplicados)
    Optional<Medico> findByCrm(Long crm);

    // Lista médicos por uma especialidade específica
    List<Medico> findByEspecialidadeIgnoreCase(String especialidade);

    // Verifica se um CPF já existe antes de salvar um novo médico
    boolean existsByCpf(Long cpf);
}
