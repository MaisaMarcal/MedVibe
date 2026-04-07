package com.tecdes.medvibe.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tecdes.medvibe.dto.PacienteDTO;
import com.tecdes.medvibe.model.Paciente;
import com.tecdes.medvibe.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    // CONSTRUTOR MANUAL: Garante a injeção sem depender do Lombok
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public PacienteDTO criarPaciente(PacienteDTO dto) {
        // Validação usando String (ajustado para evitar erros de tipo)
        if (pacienteRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("Paciente com este CPF já cadastrado.");
        }

        // INSTANCIAÇÃO MANUAL: Substitui o .builder() que não está sendo gerado
        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setIdade(dto.idade());
        paciente.setSintoma(dto.sintoma());

        return converterParaDTO(pacienteRepository.save(paciente));
    }

    public List<PacienteDTO> listarPacientes() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado!"));
        return converterParaDTO(paciente);
    }

    @Transactional
    public PacienteDTO atualizarPacientePut(Long id, PacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado!"));

        paciente.setNome(dto.nome());  
        paciente.setCpf(dto.cpf());
        paciente.setIdade(dto.idade());
        paciente.setSintoma(dto.sintoma());

        return converterParaDTO(pacienteRepository.save(paciente));
    }

    @Transactional
    public void excluirPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Erro: Paciente ID " + id + " inexistente.");
        }
        pacienteRepository.deleteById(id);
    }

    // CONVERSOR MANUAL: Essencial para o Requisito B
    private PacienteDTO converterParaDTO(Paciente p) {
        return new PacienteDTO(p.getId(), p.getNome(), p.getCpf(),p.getIdade(), p.getSintoma());
    }
}