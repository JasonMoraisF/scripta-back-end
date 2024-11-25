package com.scripta.scripta_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scripta.scripta_api.model.Livro;
import com.scripta.scripta_api.repository.LivroRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRep;

    public List<Livro> findAll() {
        return livroRep.findAll();
    }

    public Livro findById(Long id) {
        return livroRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Livro Não Encontrado"));
    }

    public Livro create(Livro livro) {
        return livroRep.save(livro);
    }

    public Livro update(Long id, Livro livroDetails) {
      Livro existingLivro = findById(id);
        
        existingLivro.setTitulo(livroDetails.getTitulo() != null ? livroDetails.getTitulo() : existingLivro.getTitulo());
        existingLivro.setAutor(livroDetails.getAutor() != null ? livroDetails.getAutor() : existingLivro.getAutor());
        existingLivro.setCategoria(livroDetails.getCategoria() != null ? livroDetails.getCategoria() : existingLivro.getCategoria());
        existingLivro.setDisponibilidade(livroDetails.isDisponibilidade() ? livroDetails.isDisponibilidade() : existingLivro.isDisponibilidade());
        
        return livroRep.save(existingLivro);
    }

    public void delete(Long id) {
        Livro existingLivro = livroRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Livro Não Encontrado"));
        livroRep.delete(existingLivro);
    }
}
