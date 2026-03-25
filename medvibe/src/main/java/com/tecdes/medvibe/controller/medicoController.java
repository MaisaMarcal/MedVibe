package com.tecdes.medvibe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecdes.medvibe.dto.MedicoDTO;
import com.tecdes.medvibe.dto.PacienteDTO;
import com.tecdes.medvibe.model.Medico;
import com.tecdes.medvibe.service.MedicoService;
;


@RestController
@RequestMapping("/medicos")
public class medicoController {
     private final MedicoService medicoService;

    // Injeção de dependência via construtor
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;


    }

      // CREATE - Endpoint (rota) para criar um aluno retornando um responseEntity
    @PostMapping("/salvar")
    public ResponseEntity<MedicoDTO> criarMedico(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO medicoCriado = medicoService.criarMedico(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCriado);
    }

    // RESTORE - Endpoint (rota para buscar todos os alunos)
    @GetMapping("/listar")
    public ResponseEntity<List<MedicoDTO>> listarMedicos() {
        List<MedicoDTO> medicos = medicoService.listarMedicos();

        if(medico.isEmpty()){
            return ResponseEntity.noContent().build();
        } 

        return ResponseEntity.ok(medicos);



    }
     // UPDATE - Endpoint (rota) para atualizar um aluno
    @PutMapping("put/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPut(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO medicoAtualizado = medicoService.atualizarMedicoPut(id, medicoDTO);
        
        return ResponseEntity.ok(medicoAtualizado);
    }

    // UPDATE - Endpoint (rota) para atualizar um aluno (parcial)
    @PatchMapping("patch/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPatch(@PathVariable Long id, @RequestBody MedicoDTO medicoTO) {
        MedicoDTO medicoAtualizado = medicoService.atualizarMedicoPatch(id, medicoDTO);
        
        return ResponseEntity.ok(medicoAtualizado);
    }

    // DELETE - Endpoint (rota) para excluir um aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id){
        
        medicoService.excluirMedico(id);

        return ResponseEntity.noContent().build();
    }
    
}
