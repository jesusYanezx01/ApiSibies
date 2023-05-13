package com.fesc.apipartidos.Services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fesc.apipartidos.shared.PartidoDto;
import com.fesc.apipartidos.shared.UsuarioDto;

//extendemos UserDetailsService para el uso de el metodo login en usuario servicio 
public interface IUsuarioService extends UserDetailsService {
    
    public UsuarioDto crearUsuario(UsuarioDto usuarioCrearDto);

    public UsuarioDto leerUsuario(String username);

    public List<PartidoDto> leerMispartidos(String username); 


}
