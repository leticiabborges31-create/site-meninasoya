package com.projeto.meninas.repository;

import com.projeto.meninas.enties.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Atividaderepository
        extends JpaRepository<Atividade, Long> {

}