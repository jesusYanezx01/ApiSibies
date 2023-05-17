package com.fesc.sibies.Services;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fesc.sibies.data.entidades.UsuarioEntity;
import com.fesc.sibies.data.repositorios.IUsuarioRepository;
import com.fesc.sibies.shared.UsuarioDto;

@Service  
public class UsuarioService implements IUsuarioService {

    
    @Autowired
    ModelMapper modelMapper; 

    @Autowired
    IUsuarioRepository iusuariorepository; 

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    

    @Override
    public UsuarioDto crearUsuario(UsuarioDto usuarioCrearDto) {

          if(iusuariorepository.findByEmail(usuarioCrearDto.getEmail()) != null){
            throw new RuntimeException("Este correo ya se encuentra en uso"); 
        } 

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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        
        UsuarioEntity usuarioEntity= iusuariorepository.findByUsername(username);


        if(username == null){
            throw new UsernameNotFoundException(username);
        }


        User usuario= new User(usuarioEntity.getUsername(), usuarioEntity.getPasswordEncripatada(), new ArrayList<>()); 
        
        return usuario; 
    }

    
    
}
