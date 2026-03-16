package com.projeto.meninas.enties;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String titulo;

    @Setter
    private String descricao;

    @Setter
    private Long idProfessor;

    public Atividade() {
    }

    public Atividade(String titulo, String descricao, Long idProfessor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.idProfessor = idProfessor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

}