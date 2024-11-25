package com.scripta.scripta_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scripta.scripta_api.model.Usuario;
import com.scripta.scripta_api.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRep;

    

    public List<Usuario> findAll(){
        return userRep.findAll();
    }

    public Usuario findByID(Long id){
        return userRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario Não Encontrado"));
    }

    //Verificação de usuario
    public boolean userVerification(String matricula, String senha){
         Usuario usuario = userRep.findByMatricula(matricula);

         if (usuario != null) {
             if(senha.equals(usuario.getSenha())){
                return true;
             }
         }
 
        // Retorna falso caso o usuário não seja encontrado ou a senha esteja incorreta
        return false;
    }
    
    public Usuario create(Usuario user){
        return userRep.save(user);
    }

    public Usuario update(Long id, Usuario userDetails){
        Usuario existingUser = userRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario Não Encontrado"));
        existingUser.setMatricula(userDetails.getMatricula() != null ? userDetails.getMatricula() : existingUser.getMatricula());
        existingUser.setNome(userDetails.getNome() != null ? userDetails.getNome() : existingUser.getNome());
        existingUser.setSenha(userDetails.getSenha() != null ? userDetails.getSenha() : existingUser.getSenha());
        existingUser.setTipoUsuario(userDetails.getTipoUsuario() != null ? userDetails.getTipoUsuario() : existingUser.getTipoUsuario());

        return userRep.save(existingUser);
    }

    public Usuario delete(Long id){
        Usuario existingUser = userRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario Não Encontrado ou ja Deletado"));
        userRep.deleteById(id);

        return existingUser;
    }
}
