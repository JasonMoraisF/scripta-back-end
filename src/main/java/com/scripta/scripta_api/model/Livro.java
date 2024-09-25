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
    
}
