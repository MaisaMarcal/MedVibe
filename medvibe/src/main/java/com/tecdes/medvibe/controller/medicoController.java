package com.tecdes.medvibe.controller;

import java.util.List;
import lombok.RequiredArgsConstructor; // Import do Lombok
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecdes.medvibe.dto.MedicoDTO;
import com.tecdes.medvibe.service.MedicoService;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor // Gera o construtor para campos 'final' (Requisito C)
public class MedicoController {

    private final MedicoService medicoService;

    // POST /medicos (Criar)
    @PostMapping
    public ResponseEntity<MedicoDTO> criarMedico(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO medicoCriado = medicoService.criarMedico(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCriado);
    }

    // GET /medicos (Listar)
    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarMedicos() {
        List<MedicoDTO> medicos = medicoService.listarMedicos();
        return medicos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(medicos);
    }

    // PUT /medicos/{id} (Atualizar Total)
    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPut(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity.ok(medicoService.atualizarMedicoPut(id, medicoDTO));
    }

    // PATCH /medicos/{id} (Atualizar Parcial)
    @PatchMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPatch(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity.ok(medicoService.atualizarMedicoPatch(id, medicoDTO));
    }

    // DELETE /medicos/{id} (Remover)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        medicoService.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }
}