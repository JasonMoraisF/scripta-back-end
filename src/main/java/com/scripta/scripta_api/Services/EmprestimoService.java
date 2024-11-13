package com.scripta.scripta_api.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scripta.scripta_api.model.Emprestimo;
import com.scripta.scripta_api.model.StatusSolicitacao;
import com.scripta.scripta_api.repository.EmprestimoRepository;
import com.scripta.scripta_api.repository.LivroRepository;
import com.scripta.scripta_api.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@EnableJpaRepositories(basePackages = "com.scripta.scripta_api.repository")
@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private LivroRepository livroRepository;
    
    private StatusSolicitacao statusSolicitacao;

    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empréstimo Não Encontrado"));
    }

    public Emprestimo create(Emprestimo emprestimoRequest) {
        Emprestimo emprestimo = new Emprestimo();
        LocalDateTime now = LocalDateTime.now();

        // emprestimo.setLivro(emprestimoRequest.getLivro());
        if (emprestimo.getDataSolicitacao() == null) {
            emprestimo.setDataSolicitacao(now);
            emprestimo.setDataDevolucao(now.plusDays(7));
            emprestimo.setStatusSolicitacao(StatusSolicitacao.AUTORIZADO);
        }else {
            emprestimo.setDataDevolucao(emprestimo.getDataSolicitacao().plusDays(7));
        }
        
        // Livro livro = livroRepository.findById(emprestimoRequest.getLivro().getLivroID()).orElseThrow(() -> new EntityNotFoundException("Livro nao encontrado pelo ID: " + emprestimoRequest.getLivro().getLivroID()));
        // Usuario usuario = usuarioRepository.findById(emprestimoRequest.getUsuario().getUsuarioID()).orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado pelo ID: " + emprestimoRequest.getUsuario().getUsuarioID()));

        // emprestimo.setLivro(livro);
        // emprestimo.setUsuario(usuario);

        return emprestimoRepository.save(emprestimo);
    }   


////////////////////////////////////////////////////////////////////////////////////////////////////////

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
