package com.scripta.scripta_api.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "Notificacao")
public class Notificacao {
    

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_notificacao")
    private Long notificacaoID;

    //Chave estrangeira do usuario

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "data_envio" , nullable=false)
    @CreatedDate
    @JsonFormat(pattern= "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataEnvio;


    @PrePersist
    protected void onCreate(){
        this.dataEnvio = LocalDateTime.now();
    }
}
