package com.projeto.meninas.Controller;

import com.projeto.meninas.Entity.Atividade;
import com.projeto.meninas.Service.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exposicao")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExposicaoController {

    private final AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<Atividade>> verExposicao() {
        return ResponseEntity.ok(atividadeService.listarAtividades());
    }
}
