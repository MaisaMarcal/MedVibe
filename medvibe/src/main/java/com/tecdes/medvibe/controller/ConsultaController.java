package com.tecdes.medvibe.controller;

import java.util.List;
import lombok.RequiredArgsConstructor; // Importação do Lombok
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecdes.medvibe.dto.ConsultaDTO;
import com.tecdes.medvibe.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor // Substitui o construtor manual (Requisito C) [cite: 294]
public class ConsultaController {

    private final ConsultaService consultaService;

    // POST: Cadastrar novo registro [cite: 256]
    @PostMapping("/salvar")
    public ResponseEntity<ConsultaDTO> criarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        // O Service agora deve validar se Medico e Paciente existem 
        ConsultaDTO consultaCriada = consultaService.criarConsulta(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaCriada);
    }

    // GET: Listar todos [cite: 257]
    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarConsultas() {
        List<ConsultaDTO> consultas = consultaService.listarConsultas();
        return consultas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(consultas);
    }

    // GET: Buscar por ID específico [cite: 258]
    @GetMapping("/listar/{id}")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    // PUT: Atualizar dados existentes [cite: 259]
    @PutMapping("/put/{id}")
    public ResponseEntity<ConsultaDTO> atualizarConsulta(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity.ok(consultaService.atualizarConsultaPut(id, consultaDTO));
    }
    // No ConsultaController.java

    // DELETE: Remover um registro [cite: 260]
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        consultaService.excluirConsulta(id);
        return ResponseEntity.noContent().build();
    }
}