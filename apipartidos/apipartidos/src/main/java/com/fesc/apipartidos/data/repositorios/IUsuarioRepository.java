package com.fesc.apipartidos.data.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.fesc.apipartidos.data.entidades.UsuarioEntity;

//Los respositorios son los codigos SQL
// @Repository Anotacion para especificar que esta clase es un repositorio
public interface IUsuarioRepository extends CrudRepository<UsuarioEntity, Long> { 
    //extendemos a la clase crud que tiene todas las clasese SQL, Especificando a la entidad con la cual esta trabajando  
    //Espefificamos tambien el tipo de ID

    public UsuarioEntity findByEmail(String email); 
     //El metodo findByy va acompañado del nombre de la tabla a la cual queremos buscar 
     public UsuarioEntity findByUsername (String username);
}
