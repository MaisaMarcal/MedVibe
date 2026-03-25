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

import com.tecdes.medvibe.dto.PacienteDTO;
import com.tecdes.medvibe.service.PacienteService;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    

    private final PacienteService pacienteService;

    // Injeção de dependência via construtor
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;


    }

      // CREATE - Endpoint (rota) para criar um aluno retornando um responseEntity
    @PostMapping("/salvar")
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody PacienteDTO pacienteDTO) {

        PacienteDTO pacienteCriado = pacienteService.criarPaciente(pacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCriado);
    }

    // RESTORE - Endpoint (rota para buscar todos os alunos)
    @GetMapping("/listar")
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        List<PacienteDTO> pacientes = pacienteService.listarPacientes();

        if(pacientes.isEmpty()){
            return ResponseEntity.noContent().build();
        } 

        return ResponseEntity.ok(pacientes);



    }
     // UPDATE - Endpoint (rota) para atualizar um aluno
    @PutMapping("put/{id}")
    public ResponseEntity<PacienteDTO> atualizarPacientePut(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
       PacienteDTO pacienteAtualizado = pacienteService.atualizarPacientePut(id, pacienteDTO);
        
        return ResponseEntity.ok(pacienteAtualizado);
    }

    // UPDATE - Endpoint (rota) para atualizar um aluno (parcial)
    @PatchMapping("patch/{id}")
    public ResponseEntity<PacienteDTO> atualizarPacientePatch(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO pacienteAtualizado = pacienteService.atualizarPacientePatch(id, pacienteDTO);
        
        return ResponseEntity.ok(pacienteAtualizado);
    }

    // DELETE - Endpoint (rota) para excluir um aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id){
        
        pacienteService.excluirPaciente(id);

        return ResponseEntity.noContent().build();
    }

}


