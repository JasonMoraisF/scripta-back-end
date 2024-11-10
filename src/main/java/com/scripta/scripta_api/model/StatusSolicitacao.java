package com.scripta.scripta_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusSolicitacao {
    PENDENTE("pendente"),
    AUTORIZADO("autorizado"),
    CANCELADO("cancelado");

    
    private final String descricao;

    StatusSolicitacao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }

    @JsonCreator
    public static StatusSolicitacao fromDescricao(String descricao){
        for(StatusSolicitacao status : StatusSolicitacao.values()){
            if(status.getDescricao().equals(descricao)){
                return status;
            }
        }
        throw new IllegalArgumentException("Status invalido: " + descricao);

    }
    @Override
    @JsonValue
    public String toString(){
        return descricao;
    }



    
}
