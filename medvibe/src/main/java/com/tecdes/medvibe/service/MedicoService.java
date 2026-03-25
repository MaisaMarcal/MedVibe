package com.tecdes.medvibe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tecdes.medvibe.dto.MedicoDTO;
import com.tecdes.medvibe.dto.PacienteDTO;
import com.tecdes.medvibe.model.Medico;
import com.tecdes.medvibe.model.Paciente;
import com.tecdes.medvibe.repository.MedicoRepository;

import jakarta.persistence.EntityNotFoundException;


   
@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;

    public MedicoService (MedicoRepository medicoRepository){
        this.medicoRepository = medicoRepository;
    }

    public MedicoDTO criarMedico(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setNome(medicoDTO.nome());
        medico.setCPFid(medicoDTO.cpfid());
        medico.setCRMid(medicoDTO.crmid());
        medico.setEspecialidade(medicoDTO.especialidade());
        Medico medicoSalvo = medicoRepository.save(medico);
        return new MedicoDTO(medicoSalvo.getId(), medicoSalvo.getNome(), medicoSalvo.getCPFid(),medicoSalvo.getCRMid(), medicoSalvo.getEspecialidade());
    }
    
    public List<MedicoDTO> listarMedicos(){
        List<Medico> medicosEntity = medicoRepository.findAll();


        return medicosEntity.stream().map(medico -> new MedicoDTO(medico.getId(), medico.getNome(), medico.getCPFid(),medico.getCRMid(), medico.getEspecialidade())).toList(); }

    //Método para atualizar um aluno (completo) - UPDATE
    public MedicoDTO atualizarMedicoPut(Long id, MedicoDTO medicoDTO){
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("medico não encontrado!"));

        medico.setNome(medicoDTO.nome());
        medico.setCPFid(medicoDTO.cpfid());
        medico.setCRMid(medicoDTO.crmid());
        medico.setEspecialidade(medicoDTO.especialidade());

        medicoRepository.save(medico);

        return new MedicoDTO(medico.getId(), medico.getNome(), medico.getCPFid(),medico.getCRMid(), medico.getEspecialidade());
    }
     // Método para atualizar um aluno (parcial) - UPDATE
    public MedicoDTO atualizarMedicoPatch(Long id,  MedicoDTO medicoDTO){
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medico não encontrado!"));

        if(medicoDTO.nome() != null){
            medico.setNome(medicoDTO.nome());
        }
        if(medicoDTO.especialidade() != null){
            medico.setEspecialidade(medicoDTO.especialidade());
        }
        medicoRepository.save(medico);

        return new MedicoDTO(medico.getId(), medico.getNome(), medico.getCPFid(),medico.getCRMid(), medico.getEspecialidade());
    }

    // Método para excluir um aluno - DELETE
    public void excluirMedico(Long id){
        if(!medicoRepository.existsById(id)){
            throw new EntityNotFoundException("O paciente com id " + id + " não existe!");
        }
        medicoRepository.deleteById(id);
    }


}



