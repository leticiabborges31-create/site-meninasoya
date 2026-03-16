package com.projeto.meninas.Service;

import com.projeto.meninas.enties.Foto;
import com.projeto.meninas.repository.Fotorepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FotoService {

    private final Fotorepository repository;

    public Foto salvarFoto(Foto foto){
        return repository.save(foto);
    }

    public List<Foto> listarFotos(){
        return repository.findAll();
    }

    public void deletarFoto(Long id){
        repository.deleteById(id);
    }
}