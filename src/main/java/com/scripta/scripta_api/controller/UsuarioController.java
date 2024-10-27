package com.scripta.scripta_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scripta.scripta_api.Services.UsuarioService;
import com.scripta.scripta_api.model.Usuario;

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
        return userServ.Create(user);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id,@RequestBody Usuario user){
        return userServ.Update(id, user);
    }

    @DeleteMapping("/{id}")
    public Usuario Delete(@PathVariable Long id){
        return userServ.delete(id);
    }

}
