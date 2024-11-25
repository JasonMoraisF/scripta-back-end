package com.scripta.scripta_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scripta.scripta_api.model.Emprestimo;
import com.scripta.scripta_api.model.Usuario;


@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    // Busca os empréstimos de um usuário com base na matrícula
    List<Emprestimo> findByUsuario(Usuario Usuario);
}
