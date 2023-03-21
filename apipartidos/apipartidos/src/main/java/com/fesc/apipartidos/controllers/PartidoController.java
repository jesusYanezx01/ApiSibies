package com.fesc.apipartidos.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fesc.apipartidos.Services.IPartidoService;
import com.fesc.apipartidos.models.peticiones.PartidoCrearRequestModel;
import com.fesc.apipartidos.models.respuestas.PartidoDataRestModel;
import com.fesc.apipartidos.shared.PartidoDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    ModelMapper modelMapper; 

    @Autowired
    IPartidoService iPartidoService; 

    

    

    @PostMapping
    public PartidoDataRestModel crearPartido(@RequestBody PartidoCrearRequestModel partidoCrearRequestModel){

        String username = "arturo";

        PartidoDto partidoCrearDto = modelMapper.map(partidoCrearRequestModel, PartidoDto.class);
        partidoCrearDto.setUsername(username);

        PartidoDto partidoDto = iPartidoService.crearPartido(partidoCrearDto);

        PartidoDataRestModel partidoDataRestModel = modelMapper.map(partidoDto, PartidoDataRestModel.class);
        
        return partidoDataRestModel;
        

    }

        
    
    
    
}
