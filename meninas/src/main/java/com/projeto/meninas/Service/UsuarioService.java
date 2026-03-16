package com.projeto.meninas.Service;

import com.projeto.meninas.enties.Usuario;
import com.projeto.meninas.repository.Usuariorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private Usuariorepository repository;


    public Usuario salvarUsuario(Usuario usuario) {

        return repository.save(usuario);

    }


    public Usuario buscarUsuarioPorEmail(String email) {

        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Email não encontrado"));

    }


    public void deletarUsuarioPorEmail(String email) {

        repository.deleteByEmail(email);

    }


    public Usuario atualizarUsuarioPorId(Long id, Usuario usuario) {

        Usuario usuarioEntity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        if(usuario.getNome() != null){
            usuarioEntity.setNome(usuario.getNome());
        }

        if(usuario.getEmail() != null){
            usuarioEntity.setEmail(usuario.getEmail());
        }

        if(usuario.getSenha() != null){
            usuarioEntity.setSenha(usuario.getSenha());
        }

        if(usuario.getTipoUsuario() != null){
            usuarioEntity.setTipoUsuario(usuario.getTipoUsuario());
        }

        return repository.save(usuarioEntity);
    }

}