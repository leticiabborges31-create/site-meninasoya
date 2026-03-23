package com.projeto.meninas.Controller;

import com.projeto.meninas.Entity.Atividade;
import com.projeto.meninas.Service.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/atividade")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AtividadeController {

    private final AtividadeService atividadeService;

    // SALVAR ATIVIDADE COM FOTOS
    @PostMapping
    public Atividade salvar(
            @RequestParam String titulo,
            @RequestParam String descricao,
            @RequestParam("fotos") MultipartFile[] fotos
    ){

        Atividade atividade = new Atividade();
        atividade.setTitulo(titulo);
        atividade.setDescricao(descricao);

        return atividadeService.salvarComFotos(atividade, fotos);
    }

    // LISTAR (para aparecer no site)
    @GetMapping
    public List<Atividade> listar(){
        return atividadeService.listarAtividades();
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        atividadeService.deletar(id);
    }
}