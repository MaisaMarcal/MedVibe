package com.tecdes.medvibe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecdes.medvibe.dto.MedicoDTO;
import com.tecdes.medvibe.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    // Injeção de dependência via construtor
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // CREATE - criar médico
    @PostMapping("/salvar")
    public ResponseEntity<MedicoDTO> criarMedico(@RequestBody MedicoDTO medicoDTO) {

        MedicoDTO medicoCriado = medicoService.criarMedico(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCriado);
    }

    // RESTORE - listar médicos
    @GetMapping("/listar")
    public ResponseEntity<List<MedicoDTO>> listarMedicos() {
        List<MedicoDTO> medicos = medicoService.listarMedicos();

        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(medicos);
    }

    // UPDATE (PUT)
    @PutMapping("put/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPut(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {

        MedicoDTO medicoAtualizado = medicoService.atualizarMedicoPut(id, medicoDTO);
        return ResponseEntity.ok(medicoAtualizado);
    }

    // UPDATE (PATCH)
    @PatchMapping("patch/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPatch(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {

        MedicoDTO medicoAtualizado = medicoService.atualizarMedicoPatch(id, medicoDTO);
        return ResponseEntity.ok(medicoAtualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {

        medicoService.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }
}