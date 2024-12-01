package com.scripta.scripta_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scripta.scripta_api.dto.EmprestimoDTO;
import com.scripta.scripta_api.model.Emprestimo;
import com.scripta.scripta_api.services.EmprestimoService;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {



    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<List<Emprestimo>> getAllEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.findAll();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> getEmprestimoById(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoService.findById(id);
        return ResponseEntity.ok(emprestimo);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Emprestimo> createEmprestimo(@RequestBody EmprestimoDTO emprestimo) {
        Emprestimo newEmprestimo = emprestimoService.create(emprestimo);
        return ResponseEntity.ok(newEmprestimo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> updateEmprestimo(@PathVariable Long id, @RequestBody Emprestimo emprestimoDetails) {
        Emprestimo updatedEmprestimo = emprestimoService.update(id, emprestimoDetails);
        return ResponseEntity.ok(updatedEmprestimo);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprestimo(@PathVariable Long id) {
    emprestimoService.delete(id);
    return ResponseEntity.noContent().build();
}

    //listar os empréstimos de um usuário pela matrícula
    @CrossOrigin(origins = "*")
    @GetMapping("/list/{matricula}")
    public ResponseEntity<List<Emprestimo>> listarEmprestimosPorMatricula(@PathVariable String matricula) {
        List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimosPorUsuario(matricula);
        if (emprestimos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 se não houverem empréstimos
        }
        return ResponseEntity.ok(emprestimos); // Retorna 200 com a lista de empréstimos
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}/renovar")
    public ResponseEntity<String> renovarEmprestimo(@PathVariable Long id) {
        return emprestimoService.renovarEmprestimo(id);
    }
}