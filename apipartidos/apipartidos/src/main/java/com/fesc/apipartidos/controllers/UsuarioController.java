package com.fesc.apipartidos.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fesc.apipartidos.Services.IUsuarioService;
import com.fesc.apipartidos.models.peticiones.UsuarioCrearRequestModel;
import com.fesc.apipartidos.models.respuestas.UsuarioDataRestModel;
import com.fesc.apipartidos.shared.UsuarioDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController //En los controladores llamamos a los servicios 
@RequestMapping("/usuario") 
public class UsuarioController {

    @Autowired //Anotacion para enlazar y buscar todas las diferentes clases y mirar cuales son BEANS para utilizarlos
    ModelMapper modelMapper;  

    @Autowired
    IUsuarioService iusuarioservice;

    @GetMapping
    public UsuarioDataRestModel leerUsuario(){

        String username = "ortegani";
        UsuarioDto usuarioDto = iusuarioservice.leerUsuario(username);

        UsuarioDataRestModel usuarioDataRestModel = modelMapper.map(usuarioDto, UsuarioDataRestModel.class);

        return usuarioDataRestModel ;
    }

    @PostMapping
    public UsuarioDataRestModel crearUsuario(@RequestBody UsuarioCrearRequestModel usuarioCrearRequestModel) {

        //Necesitamos mapear la informacion para guardarlo en el DTO 

        UsuarioDto usuarioCrearDto = modelMapper.map(usuarioCrearRequestModel, UsuarioDto.class); //Mapea el primer objeto y lo convierte en el siguiente

        UsuarioDto usuariodto = iusuarioservice.crearUsuario(usuarioCrearDto);

        UsuarioDataRestModel usuariodatarestmodel = modelMapper.map(usuariodto,UsuarioDataRestModel.class);
        
        //Vamos a registrar la informacion al servicio 
        
        return usuariodatarestmodel;
    }
    

    
}
