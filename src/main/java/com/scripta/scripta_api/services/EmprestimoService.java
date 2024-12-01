package com.scripta.scripta_api.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scripta.scripta_api.dto.EmprestimoDTO;
import com.scripta.scripta_api.model.Emprestimo;
import com.scripta.scripta_api.model.StatusSolicitacao;
import com.scripta.scripta_api.model.Usuario;
import com.scripta.scripta_api.model.Livro;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empréstimo Não Encontrado"));
    }

    public Emprestimo create(EmprestimoDTO emprestimoRequest) {
       
        Emprestimo emprestimo = new Emprestimo();

        Usuario usuario = usuarioService.findByID(emprestimoRequest.getUsuarioID());
        Livro livro = livroService.findById(emprestimoRequest.getLivroID());

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setStatusSolicitacao(StatusSolicitacao.AUTORIZADO);

        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataDevolucao = dataAtual.plusDays(7);
        //LocalDateTime dataDevolucao = dataAtual.minusDays(2);
        //LocalDateTime dataDevolucao2 = dataAtual.minusDays(9);
        
        
        emprestimo.setDataSolicitacao(dataAtual);
        emprestimo.setDataAprovacao(dataAtual);
        emprestimo.setDataDevolucao(dataDevolucao);
        
        
       return emprestimoRepository.save(emprestimo);
           //return emprestimo;                                  
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

    // Método para buscar os empréstimos de um usuário pela matrícula
    public List<Emprestimo> buscarEmprestimosPorUsuario(String matricula) {
        Usuario user = usuarioRepository.findByMatricula(matricula);
        return emprestimoRepository.findByUsuario(user);
    }

    //metodo de renovação - atualização da data de devolução
        public ResponseEntity<String> renovarEmprestimo(Long emprestimoID) {
        // Busca o empréstimo pelo ID
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoID)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo Não Encontrado"));

        LocalDateTime dataDevolucaoAtual = emprestimo.getDataDevolucao();
        LocalDateTime dataAtual = LocalDateTime.now();

        // Se a data de devolução já passou, o usuário não pode renovar
        if (dataDevolucaoAtual.isBefore(dataAtual)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O prazo para renovação já expirou.");
        }

        LocalDateTime novaDataDevolucao = dataDevolucaoAtual.plusDays(7);
        emprestimo.setDataDevolucao(novaDataDevolucao);

        emprestimoRepository.save(emprestimo);

        return ResponseEntity.ok("Empréstimo renovado com sucesso! Nova data de devolução: " + novaDataDevolucao);
    }

}// class
