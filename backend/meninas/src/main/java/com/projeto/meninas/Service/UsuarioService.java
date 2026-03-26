package com.projeto.meninas.Service;

import com.projeto.meninas.Entity.Usuario;
import com.projeto.meninas.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ✅ CADASTRAR USUÁRIO (CRIPTOGRAFANDO SENHA)
    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    // ✅ BUSCAR POR EMAIL
    public Usuario buscarUsuarioPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }

    // ✅ DELETAR
    public void deletarUsuarioPorEmail(String email) {
        repository.deleteByEmail(email);
    }

    // ✅ ATUALIZAR (COM SEGURANÇA NA SENHA)
    public Usuario atualizarUsuarioPorId(Long id, Usuario usuario) {
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuario.getNome() != null) entity.setNome(usuario.getNome());
        if (usuario.getEmail() != null) entity.setEmail(usuario.getEmail());

        // 🔐 CRIPTOGRAFA SE ALTERAR SENHA
        if (usuario.getSenha() != null) {
            entity.setSenha(encoder.encode(usuario.getSenha()));
        }

        if (usuario.getTipoUsuario() != null) {
            entity.setTipoUsuario(usuario.getTipoUsuario());
        }

        return repository.save(entity);
    }

    // ✅ LOGIN SEGURO
    public boolean autenticar(String email, String senha) {
        return repository.findByEmail(email)
                .map(u -> encoder.matches(senha, u.getSenha()))
                .orElse(false);
    }
}