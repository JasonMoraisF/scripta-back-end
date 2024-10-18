package com.scripta.scripta_api.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "Emprestimo")
public class Emprestimo {
    

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_emprestimo")
    private Long emprestimoID;


    //Chave estrangeira de usuario


    //Chave estrangeira de livro

    @Column(name = "data_solicitacao")
    @CreatedDate
    @JsonFormat(pattern= "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataSolicitacao;

    @Column(name = "status_solicitacao")
    @Enumerated
    private StatusSolicitacao statusSolicitacao;

    @Column(name = "data_aprovacao")
    @JsonFormat(pattern= "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataAprovacao;

    @Column(name = "data_rejeicao")
    @JsonFormat(pattern= "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataRejeicao;

    @Column(name = "data_devolucao")
    @JsonFormat(pattern= "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataDevolucao;

    @PrePersist
    protected void onCreate(){
        this.dataSolicitacao = LocalDateTime.now();
    }
}
