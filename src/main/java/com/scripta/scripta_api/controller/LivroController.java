package com.scripta.scripta_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scripta.scripta_api.Services.LivroService;
import com.scripta.scripta_api.model.Livro;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> getAllLivros() {
        return livroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {
        Livro livro = livroService.findById(id);
        return ResponseEntity.ok(livro);
    } 
    @PostMapping
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
        Livro novoLivro = livroService.create(livro);
        return ResponseEntity.ok(novoLivro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody Livro livroDetails) {
        Livro livroAtualizado = livroService.update(id, livroDetails);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
