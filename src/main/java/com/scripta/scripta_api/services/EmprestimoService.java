package com.scripta.scripta_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scripta.scripta_api.dto.EmprestimoDTO;
import com.scripta.scripta_api.model.Emprestimo;
import com.scripta.scripta_api.model.StatusSolicitacao;
import com.scripta.scripta_api.model.Usuario;
import com.scripta.scripta_api.repository.EmprestimoRepository;
import com.scripta.scripta_api.repository.LivroRepository;
import com.scripta.scripta_api.repository.UsuarioRepository;
import com.scripta.scripta_api.services.UsuarioService;

import jakarta.persistence.EntityNotFoundException;

@EnableJpaRepositories(basePackages = "com.scripta.scripta_api.repository")
@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioService usuarioService;

    
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empréstimo Não Encontrado"));
    }

    public Emprestimo create(EmprestimoDTO emprestimoRequest) {
       Usuario usuario = 
                                            
    }   


    public Emprestimo update(Long id, Emprestimo emprestimoDetails) {
        Emprestimo existingEmprestimo = findById(id);

        existingEmprestimo.setStatusSolicitacao(emprestimoDetails.getStatusSolicitacao() != null ? emprestimoDetails.getStatusSolicitacao() : existingEmprestimo.getStatusSolicitacao());
        existingEmprestimo.setDataAprovacao(emprestimoDetails.getDataAprovacao() != null ? emprestimoDetails.getDataAprovacao() : existingEmprestimo.getDataAprovacao());
        existingEmprestimo.setDataRejeicao(emprestimoDetails.getDataRejeicao() != null ? emprestimoDetails.getDataRejeicao() : existingEmprestimo.getDataRejeicao());
        existingEmprestimo.setDataDevolucao(emprestimoDetails.getDataDevolucao() != null ? emprestimoDetails.getDataDevolucao() : existingEmprestimo.getDataDevolucao());

        return emprestimoRepository.save(existingEmprestimo);
    }

    public ResponseEntity<Void> delete(Long id) {
        Emprestimo existingEmprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empréstimo Não Encontrado"));
        emprestimoRepository.delete(existingEmprestimo);
        return null;
    }
}
