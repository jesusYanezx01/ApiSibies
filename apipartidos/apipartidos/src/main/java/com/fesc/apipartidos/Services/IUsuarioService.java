package com.fesc.apipartidos.Services;

import com.fesc.apipartidos.shared.UsuarioDto;

public interface IUsuarioService {
    
    public UsuarioDto crearUsuario(UsuarioDto usuarioCrearDto);

    public UsuarioDto leerUsuario(String username);
}
