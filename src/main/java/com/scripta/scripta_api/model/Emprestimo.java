package com.scripta.scripta_api.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "Emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emprestimo")
    private Long emprestimoID;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id",  insertable = false, updatable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "livro_id",  insertable = false, updatable = false)
    private Livro livro;

    @Column(name = "data_solicitacao", nullable=true)
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataSolicitacao;
    
    @Column(name = "status_solicitacao", nullable=true)
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao;
    
    @Column(name = "data_aprovacao", nullable=true)
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataAprovacao;
    
    @Column(name = "data_rejeicao", nullable=true)
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataRejeicao;
    
    @Column(name = "data_devolucao", nullable=true)
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataDevolucao;

    @PrePersist
    protected void onCreate() {
        this.dataSolicitacao = LocalDateTime.now();
    }
    
    public Long getEmprestimoID() {
        return emprestimoID;
    }
    
    public void setEmprestimoID(Long emprestimoID) {
        this.emprestimoID = emprestimoID;
    }
    
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public StatusSolicitacao getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(LocalDateTime dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public LocalDateTime getDataRejeicao() {
        return dataRejeicao;
    }

    public void setDataRejeicao(LocalDateTime dataRejeicao) {
        this.dataRejeicao = dataRejeicao;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
