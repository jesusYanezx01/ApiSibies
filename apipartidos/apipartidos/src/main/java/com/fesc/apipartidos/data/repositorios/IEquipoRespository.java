package com.fesc.apipartidos.data.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.fesc.apipartidos.data.entidades.EquipoEntity;


public interface IEquipoRespository extends CrudRepository<EquipoEntity,Long> {

    public EquipoEntity findById(long id);


    
    
}
