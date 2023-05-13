package com.fesc.apipartidos.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fesc.apipartidos.Services.IUsuarioService;
import com.fesc.apipartidos.models.peticiones.UsuarioCrearRequestModel;
import com.fesc.apipartidos.models.respuestas.PartidoDataRestModel;
import com.fesc.apipartidos.models.respuestas.UsuarioDataRestModel;
import com.fesc.apipartidos.shared.PartidoDto;
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

        String username = "rortegani";
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
    
    //Utilizaremos el metodo Get porque es para obtener informacion 
    //utilizamos path 
    @GetMapping(path = "/mispartidos")
    public List<PartidoDataRestModel> leerMispartidos() {
        //debe a ver una validacion de login para leer mis partidos
        String username = "rortegani";

        //llamamos el metodo leerMispartidos de la interfaz del servicio usuario 
        //Este metodo nos traera una lista partidoDto 
        List<PartidoDto> partidoDtoList=  iusuarioservice.leerMispartidos(username);
        
        //creamos un objeto llamado partidoDataRestModelsList
        List<PartidoDataRestModel> partidoDataRestModelsList = new ArrayList<>(); 

        //iteramos sobre partidoDtoList y almacenamos dentro de partidoDto
        for(PartidoDto partidoDto : partidoDtoList){
            //mapeamos partidoDto en partidoDataRestModel
            PartidoDataRestModel partidoDataRestModel= modelMapper.map(partidoDto,PartidoDataRestModel.class);

            //agregamos un condicional que nos compare la fecha del partido con la fecha actual y si es menor que 0
            if(partidoDataRestModel.getFecha().compareTo(new Date(System.currentTimeMillis())) < 0){
                //valida true, ya que el partida ya seria jugado 
                partidoDataRestModel.setJugado(true);
            }
            //agregamos partidoDataRestModel al arreglo para poder retonarlo al metodo
            partidoDataRestModelsList.add(partidoDataRestModel);
        }
        
        
        return partidoDataRestModelsList;
    }
    
    

    
}
