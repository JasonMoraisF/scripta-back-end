package com.scripta.scripta_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scripta.scripta_api.model.Notificacao;


@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>{

}
