package com.fesc.sibies.controllers;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fesc.sibies.Services.IUsuarioService;
import com.fesc.sibies.models.peticiones.UsuarioCrearRequestModel;
import com.fesc.sibies.models.respuestas.UsuarioDataRestModel;
import com.fesc.sibies.shared.UsuarioDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController  
@RequestMapping("/usuario") 
public class UsuarioController {

    @Autowired 
    ModelMapper modelMapper;  

    @Autowired
    IUsuarioService iusuarioservice;

    @GetMapping
    public UsuarioDataRestModel leerUsuario(){

        String username = "arturo";

        UsuarioDto usuarioDto = iusuarioservice.leerUsuario(username);

        UsuarioDataRestModel usuarioDataRestModel = modelMapper.map(usuarioDto, UsuarioDataRestModel.class);

        return usuarioDataRestModel ;
    }

    @PostMapping
    public UsuarioDataRestModel crearUsuario(@RequestBody UsuarioCrearRequestModel usuarioCrearRequestModel) {


        UsuarioDto usuarioCrearDto = modelMapper.map(usuarioCrearRequestModel, UsuarioDto.class); 

        UsuarioDto usuariodto = iusuarioservice.crearUsuario(usuarioCrearDto);

        UsuarioDataRestModel usuariodatarestmodel = modelMapper.map(usuariodto,UsuarioDataRestModel.class);
        
        
        return usuariodatarestmodel;
    }
    
    
    
    

    
}
