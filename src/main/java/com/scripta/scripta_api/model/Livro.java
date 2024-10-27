package com.scripta.scripta_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Livro")
public class Livro {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Long livroID;
    
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autor")
    private String autor;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "disponibilidade")
    private boolean disponibilidade;

   public Long getLivroID() {
        return livroID;
    }

    public void setLivroID(Long livroID) {
        this.livroID = livroID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
