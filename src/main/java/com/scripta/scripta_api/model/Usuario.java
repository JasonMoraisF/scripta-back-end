package com.scripta.scripta_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="Usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long usuarioID;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "tipo_usuario")
    @Enumerated
    private TipoUsuario tipoUsuario;
}
