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

    // CONSTRUTOR MANUAL (Substitui o @RequiredArgsConstructor que estava falhando)
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> criarMedico(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO medicoCriado = medicoService.criarMedico(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCriado);
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarMedicos() {
        List<MedicoDTO> medicos = medicoService.listarMedicos();
        return medicos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(medicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPut(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity.ok(medicoService.atualizarMedicoPut(id, medicoDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPatch(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity.ok(medicoService.atualizarMedicoPatch(id, medicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        medicoService.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }
}