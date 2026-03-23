package com.projeto.meninas.repository;

import com.projeto.meninas.enties.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fotorepository extends JpaRepository<Foto, Long> {

}