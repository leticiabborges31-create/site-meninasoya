package com.projeto.meninas.Controller;

import com.projeto.meninas.Entity.Atividade;
import com.projeto.meninas.Service.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividade")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AtividadeController {

    private final AtividadeService atividadeService;

    // SALVAR ATIVIDADE COM FOTOS
    @PostMapping
    public ResponseEntity<Atividade> salvar(@RequestBody Atividade atividade) {
        return ResponseEntity.ok(atividadeService.salvar(atividade));
    }

    // LISTAR (para aparecer no site)
    @GetMapping
    public ResponseEntity<List<Atividade>> listar() {
        return ResponseEntity.ok(atividadeService.listarAtividades());
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atividadeService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
