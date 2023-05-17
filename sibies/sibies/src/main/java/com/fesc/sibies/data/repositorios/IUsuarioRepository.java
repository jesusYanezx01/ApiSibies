package com.fesc.sibies.data.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.fesc.sibies.data.entidades.UsuarioEntity;


public interface IUsuarioRepository extends CrudRepository<UsuarioEntity, Long> { 
    
    public UsuarioEntity findByEmail(String email); 

     public UsuarioEntity findByUsername (String username);
}
