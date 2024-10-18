package com.scripta.scripta_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoUsuario {
    LEITOR("leitor"),
    BIBLIOTECARIO("bibliotecario");

    private String descricao;

    TipoUsuario(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }

    @JsonCreator
    public static TipoUsuario fromDescricao(String descricao){
        for(TipoUsuario tipo : TipoUsuario.values()){
            if(tipo.getDescricao().equals(descricao)){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo do Usuario invalido: " + descricao);
    }

    @Override
    @JsonValue
    public String toString(){
        return descricao;
    }

}
