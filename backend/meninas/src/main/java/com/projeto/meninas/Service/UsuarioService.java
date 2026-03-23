package com.projeto.meninas.Service;

import com.projeto.meninas.Entity.Usuario;
import com.projeto.meninas.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario salvarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }

    public void deletarUsuarioPorEmail(String email) {
        repository.deleteByEmail(email);
    }

    public Usuario atualizarUsuarioPorId(Long id, Usuario usuario) {
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuario.getNome() != null) entity.setNome(usuario.getNome());
        if (usuario.getEmail() != null) entity.setEmail(usuario.getEmail());
        if (usuario.getSenha() != null) entity.setSenha(usuario.getSenha());
        if (usuario.getTipoUsuario() != null) entity.setTipoUsuario(usuario.getTipoUsuario());

        return repository.save(entity);
    }

    public boolean autenticar(String email, String senha) {
        return repository.findByEmail(email)
                .map(u -> u.getSenha().equals(senha))
                .orElse(false);
    }
}
