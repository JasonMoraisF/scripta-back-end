package com.scripta.scripta_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scripta.scripta_api.model.Usuario;
import com.scripta.scripta_api.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioService userServ;

    @GetMapping
    public List<Usuario> getAll(){
        return userServ.findAll();
    }
    
    @GetMapping("/{id}")
    public Usuario getByID(@PathVariable Long id){
        return userServ.findByID(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuario Create(@RequestBody Usuario user){
        return userServ.create(user);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id,@RequestBody Usuario user){
        return userServ.update(id, user);
    }

    @DeleteMapping("/{id}")
    public Usuario Delete(@PathVariable Long id){
        return userServ.delete(id);
    }

    @CrossOrigin(origins = "*") // anotação que permite que qualquer dominio faça requisição
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String matricula, @RequestParam String senha) {
    // Verificar se o login está correto
    boolean loginValido = userServ.userVerification(matricula, senha);

    if (loginValido) {
        return ResponseEntity.ok("Login bem-sucedido");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
}


}
