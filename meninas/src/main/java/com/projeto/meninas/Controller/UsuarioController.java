package com.projeto.meninas.Controller;

import com.projeto.meninas.Service.UsuarioService;
import com.projeto.meninas.enties.Usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class UsuarioController {

    private final UsuarioService usuarioService;


    // SALVAR USUARIO
    @PostMapping
    public ResponseEntity<Void> salvarUsuario(
            @RequestBody Usuario usuario){

        usuarioService.salvarUsuario(usuario);

        return ResponseEntity.ok().build();

    }


    // BUSCAR POR EMAIL
    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(
            @RequestParam String email){

        return ResponseEntity.ok(
                usuarioService.buscarUsuarioPorEmail(email));

    }


    // DELETAR
    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(
            @RequestParam String email){

        usuarioService.deletarUsuarioPorEmail(email);

        return ResponseEntity.ok().build();

    }


    // ATUALIZAR
    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuarioPorId(
            @RequestParam Long id,
            @RequestBody Usuario usuario){

        return ResponseEntity.ok(
                usuarioService.atualizarUsuarioPorId(id, usuario));

    }

}