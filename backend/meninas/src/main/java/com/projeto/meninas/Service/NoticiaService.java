package com.projeto.meninas.Service;

import com.projeto.meninas.Entity.Atividade;
import com.projeto.meninas.Repository.AtividadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtividadeService {

    private final AtividadeRepository repository;

    // SALVAR
    public Atividade salvar(Atividade atividade){
        return repository.save(atividade);
    }

    // LISTAR
    public List<Atividade> listarAtividades(){
        return repository.findAll();
    }
    public Atividade salvarComFotos(Atividade atividade, MultipartFile[] fotos){

        System.out.println("Titulo: " + atividade.getTitulo());
        System.out.println("Descricao: " + atividade.getDescricao());
        System.out.println("Quantidade de fotos: " + fotos.length);

        return repository.save(atividade);
    }

    // DELETAR
    public void deletar(Long id){
        repository.deleteById(id);
    }
}