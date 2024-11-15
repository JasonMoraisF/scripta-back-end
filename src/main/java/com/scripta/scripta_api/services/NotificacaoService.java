package com.scripta.scripta_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scripta.scripta_api.model.Notificacao;
import com.scripta.scripta_api.repository.NotificacaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NotificacaoService {
    
    @Autowired
    NotificacaoRepository notiRep;

    public List<Notificacao> findAll(){
        return notiRep.findAll();
    }

    public Notificacao findByID(Long id){
        return notiRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Notificação Expirada ou Não Encontrado"));
    }

    public Notificacao Create(Notificacao notiDetails){
        return notiRep.save(notiDetails);
    }

    public Notificacao Update(Long id, Notificacao notiDetails){
        Notificacao existingNotificao = findByID(id);

        existingNotificao.setDataEnvio(notiDetails.getDataEnvio() != null ? notiDetails.getDataEnvio() : existingNotificao.getDataEnvio());
        existingNotificao.setMensagem(notiDetails.getMensagem() != null ? notiDetails.getMensagem() : existingNotificao.getMensagem());

        return notiRep.save(existingNotificao);
    }

    public Notificacao delete(Long id){
        Notificacao existingNotificacao = notiRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Notificação deletada ou não encontrada"));
        notiRep.deleteById(id);
        return existingNotificacao;
    }
}
