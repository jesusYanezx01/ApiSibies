package com.fesc.sibies.Services;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.fesc.sibies.shared.UsuarioDto;

public interface IUsuarioService extends UserDetailsService {
    
    public UsuarioDto crearUsuario(UsuarioDto usuarioCrearDto);

    public UsuarioDto leerUsuario(String username);

   


}
