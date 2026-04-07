package com.tecdes.medvibe.service;

import com.tecdes.medvibe.dto.ConsultaDTO;
import com.tecdes.medvibe.model.Consulta;
import com.tecdes.medvibe.model.Medico;
import com.tecdes.medvibe.model.Paciente;
import com.tecdes.medvibe.repository.ConsultaRepository;
import com.tecdes.medvibe.repository.MedicoRepository;
import com.tecdes.medvibe.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    // No ConsultaService.java

public ConsultaDTO criarConsulta(ConsultaDTO dto) {
    // Busca as entidades para garantir a integridade (Requisito Etapa 2)
    Medico medico = medicoRepository.findById(dto.medicoId())
            .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

    Paciente paciente = pacienteRepository.findById(dto.pacienteId())
            .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

    // Criando o objeto manualmente (Substitui o Builder que dava erro)
    Consulta consulta = new Consulta();
    consulta.setDataHora(dto.dataHora());
    consulta.setMedico(medico);
    consulta.setPaciente(paciente);

    Consulta salva = consultaRepository.save(consulta);
    return converterParaDTO(salva);
}



    // Método que o Controller chama na linha 37
    public ConsultaDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada!"));
        return converterParaDTO(consulta);
    }

    // Método que o Controller chama na linha 43
    public ConsultaDTO atualizarConsultaPut(Long id, ConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada!"));

        // Validação de Integridade: Busca médico e paciente reais
        Medico medico = medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado!"));
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado!"));

        consulta.setDataHora(dto.dataHora());
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        return converterParaDTO(consultaRepository.save(consulta));
    }

    public List<ConsultaDTO> listarConsultas() {
        return consultaRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    // Método auxiliar essencial para o Requisito B

    private ConsultaDTO converterParaDTO(Consulta c) {
        return new ConsultaDTO(
        c.getId(), 
        c.getDataHora(), 
        c.getMedico().getId(), 
        c.getPaciente().getId(),
        c.getMedico().getNome(), 
        c.getPaciente().getNome()
    );
}
    
    // Corrigindo o erro da linha 50 do Controller
    public void excluirConsulta(Long id) {
    if (!consultaRepository.existsById(id)) {
        throw new EntityNotFoundException("Consulta não encontrada");
    }
    consultaRepository.deleteById(id);
}
}