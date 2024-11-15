package com.scripta.scripta_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scripta.scripta_api.model.Notificacao;
import com.scripta.scripta_api.services.NotificacaoService;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {
    
    @Autowired
    NotificacaoService notiServ;

    @GetMapping
    public List<Notificacao> findAll(){
        return notiServ.findAll();
    }

    @GetMapping("/{id}")
        public ResponseEntity<Notificacao> getNotificacaoById(@PathVariable Long id) {
            Notificacao notificacao = notiServ.findByID(id);
            return ResponseEntity.ok(notificacao);
            
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Notificacao create(@RequestBody Notificacao notiDetails){
        return notiServ.Create(notiDetails);
    }

    @PutMapping("/{id}")
    public Notificacao update(@PathVariable Long id,@RequestBody Notificacao notiDetails){
        return notiServ.Update(id, notiDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notificacao> deleteNoficação(@PathVariable Long id){
        Notificacao deletedNotificacao = notiServ.delete(id);
        return ResponseEntity.ok(deletedNotificacao);
    }
}
