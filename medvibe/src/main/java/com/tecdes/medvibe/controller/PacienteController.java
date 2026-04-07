package com.tecdes.medvibe.controller;

import java.util.List;
import lombok.RequiredArgsConstructor; // Importante para o Requisito C
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecdes.medvibe.dto.PacienteDTO;
import com.tecdes.medvibe.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor // Gera o construtor automaticamente (Lombok)
public class PacienteController {

    private final PacienteService pacienteService;

    // POST /pacientes
    @PostMapping
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO pacienteCriado = pacienteService.criarPaciente(pacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCriado);
    }

    // GET /pacientes
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        List<PacienteDTO> pacientes = pacienteService.listarPacientes();
        return pacientes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pacientes);
    }

    // GET /pacientes/{id}
    @GetMapping("listar/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    // PUT /pacientes/{id}
    @PutMapping("/put/{id}")
    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.atualizarPacientePut(id, pacienteDTO));
    }

    // DELETE /pacientes/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);
        return ResponseEntity.noContent().build();
    }
}