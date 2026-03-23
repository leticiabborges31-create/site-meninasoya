package com.projeto.meninas.repository;

import com.projeto.meninas.enties.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Usuariorepository
        extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    void deleteByEmail(String email);

}