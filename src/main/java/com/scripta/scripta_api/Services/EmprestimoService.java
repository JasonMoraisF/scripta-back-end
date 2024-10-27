package com.scripta.scripta_api.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scripta.scripta_api.model.Emprestimo;
import com.scripta.scripta_api.repository.EmprestimoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRep;

    public List<Emprestimo> findAll() {
        return emprestimoRep.findAll();
    }

    public Emprestimo findById(Long id) {
        return emprestimoRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Empréstimo Não Encontrado"));
    }

    public Emprestimo create(Emprestimo emprestimo) {
        return emprestimoRep.save(emprestimo);
    }

    public Emprestimo update(Long id, Emprestimo emprestimoDetails) {
        Emprestimo existingEmprestimo = findById(id);

        existingEmprestimo.setStatusSolicitacao(emprestimoDetails.getStatusSolicitacao() != null ? emprestimoDetails.getStatusSolicitacao() : existingEmprestimo.getStatusSolicitacao());
        existingEmprestimo.setDataAprovacao(emprestimoDetails.getDataAprovacao() != null ? emprestimoDetails.getDataAprovacao() : existingEmprestimo.getDataAprovacao());
        existingEmprestimo.setDataRejeicao(emprestimoDetails.getDataRejeicao() != null ? emprestimoDetails.getDataRejeicao() : existingEmprestimo.getDataRejeicao());
        existingEmprestimo.setDataDevolucao(emprestimoDetails.getDataDevolucao() != null ? emprestimoDetails.getDataDevolucao() : existingEmprestimo.getDataDevolucao());

        return emprestimoRep.save(existingEmprestimo);
    }

    public void delete(Long id) {
        Emprestimo existingEmprestimo = emprestimoRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Empréstimo Não Encontrado"));
        emprestimoRep.delete(existingEmprestimo);
    }
}
