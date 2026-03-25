package com.tecdes.medvibe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecdes.medvibe.dto.ConsultaDTO;
import com.tecdes.medvibe.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    // Injeção de dependência via construtor
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    // CREATE - criar consulta
    @PostMapping("/salvar")
    public ResponseEntity<ConsultaDTO> criarConsulta(@RequestBody ConsultaDTO consultaDTO) {

        ConsultaDTO consultaCriada = consultaService.criarConsulta(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaCriada);
    }

    // RESTORE - listar consultas
    @GetMapping("/listar")
    public ResponseEntity<List<ConsultaDTO>> listarConsultas() {

        List<ConsultaDTO> consultas = consultaService.listarConsultas();

        if (consultas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(consultas);
    }

    // UPDATE (PUT)
    @PutMapping("put/{id}")
    public ResponseEntity<ConsultaDTO> atualizarConsultaPut(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {

        ConsultaDTO consultaAtualizada = consultaService.atualizarConsultaPut(id, consultaDTO);
        return ResponseEntity.ok(consultaAtualizada);
    }

    // UPDATE (PATCH)
    @PatchMapping("patch/{id}")
    public ResponseEntity<ConsultaDTO> atualizarConsultaPatch(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {

        ConsultaDTO consultaAtualizada = consultaService.atualizarConsultaPatch(id, consultaDTO);
        return ResponseEntity.ok(consultaAtualizada);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {

        consultaService.excluirConsulta(id);
        return ResponseEntity.noContent().build();
    }
}