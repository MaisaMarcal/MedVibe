package com.tecdes.medvibe.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tecdes.medvibe.dto.MedicoDTO;
import com.tecdes.medvibe.model.Medico;
import com.tecdes.medvibe.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    // CONSTRUTOR MANUAL (Substitui o @RequiredArgsConstructor)
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    // CREATE
    public MedicoDTO criarMedico(MedicoDTO dto) {
        if (medicoRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("Já existe um médico cadastrado com este CPF.");
        }

        // INSTANCIAÇÃO MANUAL (Substitui o .builder())
        Medico medico = new Medico();
        medico.setNome(dto.nome());
        medico.setCpf(dto.cpf());
        medico.setCrm(dto.crm());
        medico.setEspecialidade(dto.especialidade());

        Medico salvo = medicoRepository.save(medico);
        return converterParaDTO(salvo);
    }

    // READ
    public List<MedicoDTO> listarMedicos() {
        return medicoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // UPDATE (PUT)
    public MedicoDTO atualizarMedicoPut(Long id, MedicoDTO dto) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado!"));

        medico.setNome(dto.nome());
        medico.setCpf(dto.cpf());
        medico.setCrm(dto.crm());
        medico.setEspecialidade(dto.especialidade());

        return converterParaDTO(medicoRepository.save(medico));
    }

    // UPDATE (PATCH)
    public MedicoDTO atualizarMedicoPatch(Long id, MedicoDTO dto) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado!"));

        if (dto.nome() != null) medico.setNome(dto.nome());
        if (dto.cpf() != null) medico.setCpf(dto.cpf());
        if (dto.crm() != null) medico.setCrm(dto.crm());
        if (dto.especialidade() != null) medico.setEspecialidade(dto.especialidade());

        return converterParaDTO(medicoRepository.save(medico));
    }

    // DELETE
    public void excluirMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Médico não encontrado!");
        }
        medicoRepository.deleteById(id);
    }

    // CONVERSÃO MANUAL (Certifique-se que o Medico.java tenha o getId() manual)
    private MedicoDTO converterParaDTO(Medico m) {
        return new MedicoDTO(
            m.getId(), 
            m.getNome(), 
            m.getCpf(), 
            m.getCrm(), 
            m.getEspecialidade()
        );
    }
}