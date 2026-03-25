package com.tecdes.medvibe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tecdes.medvibe.dto.ConsultaDTO;
import com.tecdes.medvibe.model.Consulta;
import com.tecdes.medvibe.repository.ConsultaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    // CREATE
    public ConsultaDTO criarConsulta(ConsultaDTO consultaDTO) {

        Consulta consulta = new Consulta();
        consulta.setDataHora(consultaDTO.dataHora());
        consulta.setPacienteid(consultaDTO.pacienteid());
        consulta.setMedicoid(consultaDTO.medicoid());
        consulta.setEnderecoid(consultaDTO.enderecoid());

        Consulta consultaSalva = consultaRepository.save(consulta);

        return new ConsultaDTO(
                consultaSalva.getId(),
                consultaSalva.getDataHora(),
                consultaSalva.getPacienteid(),
                consultaSalva.getMedicoid(),
                consultaSalva.getEnderecoid()
        );
    }

    // READ
    public List<ConsultaDTO> listarConsultas() {

        List<Consulta> consultas = consultaRepository.findAll();

        return consultas.stream().map(c -> new ConsultaDTO(
                c.getId(),
                c.getDataHora(),
                c.getPacienteid(),
                c.getMedicoid(),
                c.getEnderecoid()
        )).toList();
    }

    // UPDATE (PUT)
    public ConsultaDTO atualizarConsultaPut(Long id, ConsultaDTO consultaDTO) {

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada!"));

        consulta.setDataHora(consultaDTO.dataHora());
        consulta.setPacienteid(consultaDTO.pacienteid());
        consulta.setMedicoid(consultaDTO.medicoid());
        consulta.setEnderecoid(consultaDTO.enderecoid());

        consultaRepository.save(consulta);

        return new ConsultaDTO(
                consulta.getId(),
                consulta.getDataHora(),
                consulta.getPacienteid(),
                consulta.getMedicoid(),
                consulta.getEnderecoid()
        );
    }

    // UPDATE (PATCH)
    public ConsultaDTO atualizarConsultaPatch(Long id, ConsultaDTO consultaDTO) {

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada!"));

        if (consultaDTO.dataHora() != null) {
            consulta.setDataHora(consultaDTO.dataHora());
        }

        if (consultaDTO.pacienteid() != null) {
            consulta.setPacienteid(consultaDTO.pacienteid());
        }

        if (consultaDTO.medicoid() != null) {
            consulta.setMedicoid(consultaDTO.medicoid());
        }

        if (consultaDTO.enderecoid() != null) {
            consulta.setEnderecoid(consultaDTO.enderecoid());
        }

        consultaRepository.save(consulta);

        return new ConsultaDTO(
                consulta.getId(),
                consulta.getDataHora(),
                consulta.getPacienteid(),
                consulta.getMedicoid(),
                consulta.getEnderecoid()
        );
    }

    // DELETE
    public void excluirConsulta(Long id) {

        if (!consultaRepository.existsById(id)) {
            throw new EntityNotFoundException("Consulta com id " + id + " não existe!");
        }

        consultaRepository.deleteById(id);
    }
}