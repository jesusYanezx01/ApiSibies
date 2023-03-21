package com.fesc.apipartidos.Services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fesc.apipartidos.data.entidades.UsuarioEntity;
import com.fesc.apipartidos.data.repositorios.IUsuarioRepository;
import com.fesc.apipartidos.shared.UsuarioDto;

@Service //Anotacion para especificar que la clase es un servicio 
public class UsuarioService implements IUsuarioService {

    //Tambien tendremos que hacer un mapeo, entonces llamamos a ModelMapper
    @Autowired
    ModelMapper modelMapper; 

    @Autowired
    IUsuarioRepository iusuariorepository; 

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UsuarioDto crearUsuario(UsuarioDto usuarioCrearDto) {

            //Validacion de correo si se encuentra en la tabla mas de una vez
          if(iusuariorepository.findByEmail(usuarioCrearDto.getEmail()) != null){
            throw new RuntimeException("Este correo ya se encuentra en uso"); //linea de codigo que representa un error estandar
        } 

             //Validacion de usuario si se encuentra en la tabla mas de una vez
        if(iusuariorepository.findByUsername(usuarioCrearDto.getUsername()) != null){
            throw new RuntimeException("Este usuario ya esta en uso");
        } 

        UsuarioEntity usuarioentityDto = modelMapper.map(usuarioCrearDto, UsuarioEntity.class);
        usuarioentityDto.setIdUsuario(UUID.randomUUID().toString());
        usuarioentityDto.setPasswordEncripatada(bCryptPasswordEncoder.encode(usuarioCrearDto.getPassword()));

        UsuarioEntity usuarioentity= iusuariorepository.save(usuarioentityDto);

        UsuarioDto usuarioDto = modelMapper.map(usuarioentity, UsuarioDto.class);

      
        return usuarioDto; 
    }

    @Override
    public UsuarioDto leerUsuario(String username) {

        UsuarioEntity usuarioEntity = iusuariorepository.findByUsername(username);

        if(usuarioEntity == null){
            throw new UsernameNotFoundException(username);
        }

        UsuarioDto usuarioDto = modelMapper.map(usuarioEntity, UsuarioDto.class);

        return usuarioDto;

    }

    
    
}
