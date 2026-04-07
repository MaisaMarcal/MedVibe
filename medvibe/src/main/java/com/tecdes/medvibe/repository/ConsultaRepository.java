package com.tecdes.medvibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tecdes.medvibe.model.Consulta;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Busca todas as consultas de um paciente específico pelo ID dele
    List<Consulta> findByPacienteId(Long pacienteId);

    // Busca todas as consultas de um médico específico
    List<Consulta> findByMedicoId(Long medicoId);

    // Verifica se já existe uma consulta marcada para aquele médico no mesmo horário
    boolean existsByMedicoIdAndDataHora(Long medicoId, String dataHora);
}