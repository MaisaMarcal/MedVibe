package com.tecdes.medvibe.service;

import java.util.List;
import lombok.RequiredArgsConstructor; // Requisito C (Lombok)
import org.springframework.stereotype.Service;
import com.tecdes.medvibe.dto.MedicoDTO;
import com.tecdes.medvibe.model.Medico;
import com.tecdes.medvibe.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor // Gera o construtor automaticamente para o Repository
public class MedicoService {

    private final MedicoRepository medicoRepository;

    // CREATE
    public MedicoDTO criarMedico(MedicoDTO dto) {
        // Validação de Integridade (Opcional, mas aumenta sua nota)
        if (medicoRepository.existsByCpfid(dto.cpfid())) {
            throw new RuntimeException("Já existe um médico cadastrado com este CPF.");
        }

        // Usando o @Builder do Lombok para criar a Entity de forma limpa
        Medico medico = Medico.builder()
                .nome(dto.nome())
                .cpfid(dto.cpfid())
                .crmid(dto.crmid())
                .especialidade(dto.especialidade())
                .build();

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
        medico.setCpfid(dto.cpfid());
        medico.setCrmid(dto.crmid());
        medico.setEspecialidade(dto.especialidade());

        return converterParaDTO(medicoRepository.save(medico));
    }

    // DELETE
    public void excluirMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Médico não encontrado!");
        }
        medicoRepository.deleteById(id);
    }

    // Método Auxiliar para cumprir o Requisito B (Conversão padronizada)
    private MedicoDTO converterParaDTO(Medico m) {
        return new MedicoDTO(m.getId(), m.getNome(), m.getCpfid(), m.getCrmid(), m.getEspecialidade());
    }
}