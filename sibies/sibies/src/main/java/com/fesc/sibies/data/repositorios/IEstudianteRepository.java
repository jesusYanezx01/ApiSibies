package com.fesc.sibies.data.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fesc.sibies.data.entidades.EstudianteEntity;


public interface IEstudianteRepository extends PagingAndSortingRepository<EstudianteEntity,Long> {

    public EstudianteEntity findByNombre(String nombre);

    public EstudianteEntity findByDocumento(String documento); 
    
    public EstudianteEntity findByCorreo(String correo);

    // @Query(nativeQuery = true, value="SELECT * FROM `estudiante`")
    // List<EstudianteEntity> estudiantesCreados(); 

    
}
