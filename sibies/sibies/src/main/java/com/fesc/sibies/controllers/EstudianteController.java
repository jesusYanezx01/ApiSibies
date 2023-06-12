package com.fesc.sibies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fesc.sibies.Services.IEstudianteService;
import com.fesc.sibies.models.peticiones.EstudianteCrearRequestModel;
import com.fesc.sibies.models.respuestas.EstudianteDataRestModel;
import com.fesc.sibies.shared.EstudianteDto;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    ModelMapper modelMapper; 

    @Autowired
    IEstudianteService iEstudianteService; 
    

    @PostMapping
    public EstudianteDataRestModel crearEstudiante(@RequestBody EstudianteCrearRequestModel estudianteCrearRequestModel){

        EstudianteDto estudianteCrearDto= modelMapper.map(estudianteCrearRequestModel, EstudianteDto.class); 

        EstudianteDto estudianteDto= iEstudianteService.crearEstudiante(estudianteCrearDto); 

        EstudianteDataRestModel estudianteDataRestModel= modelMapper.map(estudianteDto, EstudianteDataRestModel.class); 

        return estudianteDataRestModel; 

    }

    /*@GetMapping
    public List<EstudianteDataRestModel> estudiantesCreados(){

        List<EstudianteDto> estudianteDtoList= iEstudianteService.estudiantesCreados();

        List<EstudianteDataRestModel> estudianteDataRestModelList= new ArrayList<>(); 

        for(EstudianteDto estudianteDto: estudianteDtoList){
            EstudianteDataRestModel estudianteDataRestModel= modelMapper.map(estudianteDto, EstudianteDataRestModel.class);

            estudianteDataRestModelList.add(estudianteDataRestModel);


        }

        return estudianteDataRestModelList; 
    } */


    
}



