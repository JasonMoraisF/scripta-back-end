package com.scripta.scripta_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scripta.scripta_api.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
