package com.fesc.sibies.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fesc.sibies.data.entidades.EstudianteEntity;
import com.fesc.sibies.data.repositorios.IEstudianteRepository;
import com.fesc.sibies.shared.EstudianteDto;

@Service
public class EstudianteService implements IEstudianteService {

    @Autowired
    ModelMapper modelMapper; 

    @Autowired
    IEstudianteRepository iEstudianteRepository; 

      

    @Override
    public EstudianteDto crearEstudiante(EstudianteDto estudianteCrearDto) {
        
        if(iEstudianteRepository.findByNombre(estudianteCrearDto.getNombre()) != null){
            throw new RuntimeException("nombre del estudiante ya registrado"); 
        } 

        if(iEstudianteRepository.findByDocumento(estudianteCrearDto.getDocumento()) != null){
            throw new RuntimeException("documento del estudiante ya registrado");
        } 

        if(iEstudianteRepository.findByCorreo(estudianteCrearDto.getCorreo()) != null){
            throw new RuntimeException("correo del estudiante ya registrado");
        } 

        EstudianteEntity estudianteEntityDto= modelMapper.map(estudianteCrearDto, EstudianteEntity.class); 
        estudianteEntityDto.setIdEstudiante(UUID.randomUUID().toString()); 

        EstudianteEntity estudianteEntity= iEstudianteRepository.save(estudianteEntityDto); 

        EstudianteDto estudianteDto= modelMapper.map(estudianteEntity, EstudianteDto.class); 

        return estudianteDto; 

    }



    // @Override
    // public List<EstudianteDto> estudiantesCreados() {

    //     List<EstudianteEntity> estudianteEntityList= iEstudianteRepository.estudiantesCreados(); 

    //     List<EstudianteDto> estudianteDtoList= new ArrayList<>(); 

    //     for(EstudianteEntity estudianteEntity: estudianteEntityList){
    //         EstudianteDto estudianteDto= modelMapper.map(estudianteEntity, EstudianteDto.class);
    //         estudianteDtoList.add(estudianteDto);

    //     }

    //     return estudianteDtoList; 
    // }

    
    
}
