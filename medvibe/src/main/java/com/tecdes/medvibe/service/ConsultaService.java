package com.tecdes.medvibe.service;

import com.tecdes.medvibe.dto.ConsultaDTO;
import com.tecdes.medvibe.model.Consulta;
import com.tecdes.medvibe.model.Medico; // Faltava este import
import com.tecdes.medvibe.model.Paciente; // Faltava este import
import com.tecdes.medvibe.repository.ConsultaRepository;
import com.tecdes.medvibe.repository.MedicoRepository;
import com.tecdes.medvibe.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List; // Faltava este import
import java.util.stream.Collectors; // Faltava este import

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository cr, MedicoRepository mr, PacienteRepository pr) {
        this.consultaRepository = cr;
        this.medicoRepository = mr;
        this.pacienteRepository = pr;
    }

    public ConsultaDTO criarConsulta(ConsultaDTO dto) {
        Consulta consulta = new Consulta();

        // Ajustado: Records usam nome() e não getNome()
        consulta.setDataHora(dto.dataHora());

        consulta.setMedico(
            medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"))
        );

        consulta.setPaciente(
            pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"))
        );

        return converterParaDTO(consultaRepository.save(consulta));
    }

    public List<ConsultaDTO> listarConsultas() {
        return consultaRepository.findAll()
            .stream()
            .map(this::converterParaDTO)
            .collect(Collectors.toList());
    }

    public ConsultaDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        return converterParaDTO(consulta);
    }

    public ConsultaDTO atualizarConsultaPut(Long id, ConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setDataHora(dto.dataHora());

        consulta.setMedico(
            medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"))
        );

        consulta.setPaciente(
            pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"))
        );

        return converterParaDTO(consultaRepository.save(consulta));
    }

    public void excluirConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

    private ConsultaDTO converterParaDTO(Consulta c) {
        // Para estas linhas funcionarem, Medico e Paciente precisam dos métodos manuais
        return new ConsultaDTO(
            c.getId(),
            c.getDataHora(),
            c.getMedico().getId(),
            c.getPaciente().getId(),
            c.getMedico().getNome(),
            c.getPaciente().getNome()
        );
    }
}