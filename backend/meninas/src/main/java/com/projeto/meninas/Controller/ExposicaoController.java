package com.projeto.meninas.Controller;


import com.projeto.meninas.enties.Atividade;
import com.projeto.meninas.Service.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exposicao")
@RequiredArgsConstructor
public class ExposicaoController {

    private final AtividadeService atividadeService;

    @GetMapping
    public List<Atividade> verExposicao(){
        return atividadeService.listarAtividades();
    }

}
