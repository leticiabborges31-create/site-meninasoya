package com.projeto.meninas.model;

import lombok.Getter;

public class Cronograma {
    @Getter
    private String data;
    @Getter
    private String atividade;
    private String status;


    public Cronograma(String data, String atividade, String status){
        this.data = data;
        this.atividade = atividade;
        this.status = status;



    }
    public String getStatus(){
        return status;
    }
}
