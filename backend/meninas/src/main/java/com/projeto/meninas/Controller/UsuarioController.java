package com.projeto.meninas.Controller;

import com.projeto.meninas.Entity.Usuario;
import com.projeto.meninas.Security.JwtUtil;
import com.projeto.meninas.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    // ✅ CADASTRO
    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    // ✅ LOGIN COM TOKEN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        boolean autenticado = usuarioService.autenticar(
                body.get("email"),
                body.get("senha")
        );

        if (!autenticado) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        String token = jwtUtil.gerarToken(body.get("email"));

        return ResponseEntity.ok(Map.of("token", token));
    }

    // ✅ BUSCAR
    @GetMapping
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    // ✅ DELETAR
    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestParam String email) {
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    // ✅ ATUALIZAR
    @PutMapping
    public ResponseEntity<Usuario> atualizar(@RequestParam Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.atualizarUsuarioPorId(id, usuario));
    }
}