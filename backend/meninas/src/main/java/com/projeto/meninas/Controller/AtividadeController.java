package com.projeto.meninas.Controller;

import com.projeto.meninas.Entity.Atividade;
import com.projeto.meninas.Service.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/atividade")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AtividadeController {

    private final AtividadeService atividadeService;

    // ✅ SALVAR ATIVIDADE
    @PostMapping
    public ResponseEntity<Atividade> salvar(
            @RequestParam String titulo,
            @RequestParam String descricao,
            @RequestParam String data,
            @RequestParam(required = false) MultipartFile foto
    ) {

        Atividade atividade = new Atividade();
        atividade.setTitulo(titulo);
        atividade.setDescricao(descricao);
        atividade.setData(data);

        // (opcional) salvar foto depois

        Atividade salva = atividadeService.salvar(atividade);

        return ResponseEntity.ok(salva);
    }

    // ✅ LISTAR
    @GetMapping
    public ResponseEntity<List<Atividade>> listar() {
        return ResponseEntity.ok(atividadeService.listarAtividades());
    }

    // ✅ DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atividadeService.deletar(id);
        return ResponseEntity.ok().build();
    }
}