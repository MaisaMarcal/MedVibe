package com.tecdes.medvibe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tecdes.medvibe.dto.PacienteDTO;
import com.tecdes.medvibe.model.Paciente;
import com.tecdes.medvibe.repository.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService (PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteDTO criarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDTO.nome());
        paciente.setCPFid(pacienteDTO.cpfid());
        paciente.setIdade(pacienteDTO.idade());
        paciente.setSintoma(pacienteDTO.sintoma());
        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        return new PacienteDTO(pacienteSalvo.getId(), pacienteSalvo.getNome(), pacienteSalvo.getCPFid(),pacienteSalvo.getIdade(), pacienteSalvo.getSintoma());
    }
    
    public List<PacienteDTO> listarPacientes(){
        List<Paciente> pacientesEntity = pacienteRepository.findAll();


        return pacientesEntity.stream().map(paciente -> new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getCPFid(),paciente.getIdade(), paciente.getSintoma())).toList();
    }
    //Método para atualizar um aluno (completo) - UPDATE
    public PacienteDTO atualizarPacientePut(Long id, PacienteDTO pacienteDTO){
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));

        paciente.setNome(pacienteDTO.nome());
        paciente.setCPFid(pacienteDTO.cpfid());
        paciente.setIdade(pacienteDTO.idade());
        paciente.setSintoma(pacienteDTO.sintoma());

        pacienteRepository.save(paciente);

        return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getCPFid(),paciente.getIdade(), paciente.getSintoma());
    }
     // Método para atualizar um aluno (parcial) - UPDATE
    public PacienteDTO atualizarPacientePatch(Long id,  PacienteDTO pacienteDTO){
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));

        if(pacienteDTO.nome() != null){
            paciente.setNome(pacienteDTO.nome());
        }
        if(pacienteDTO.idade() != null){
            paciente.setIdade(pacienteDTO.idade());
        }
        if(pacienteDTO.sintoma() != null){
            paciente.setSintoma(pacienteDTO.sintoma());
        }
        pacienteRepository.save(paciente);

        return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getCPFid(),paciente.getIdade(), paciente.getSintoma());
    }

    // Método para excluir um aluno - DELETE
    public void excluirPaciente(Long id){
        if(!pacienteRepository.existsById(id)){
            throw new EntityNotFoundException("O paciente com id " + id + " não existe!");
        }
        pacienteRepository.deleteById(id);
    }


}
