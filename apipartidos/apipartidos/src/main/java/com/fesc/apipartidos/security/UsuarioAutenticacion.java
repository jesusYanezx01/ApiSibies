package com.fesc.apipartidos.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fesc.apipartidos.Services.IUsuarioService;
import com.fesc.apipartidos.models.peticiones.UsuarioSingupRequestModel;
import com.fesc.apipartidos.shared.UsuarioDto;
import com.fesc.apipartidos.utils.AppContexto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//Esta clase es para generar el token de cada usuario que inicie sesion en mi login 
public class UsuarioAutenticacion extends UsernamePasswordAuthenticationFilter {

    

    private final AuthenticationManager authenticationManager; 



    public UsuarioAutenticacion(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //metodo para la autenticacion del usuario en el login 
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
                try {
                    //modelaje de datos con ObjectMapper
                    UsuarioSingupRequestModel usuarioSingupRequestModel= new ObjectMapper().readValue(request.getInputStream(), UsuarioSingupRequestModel.class);

                    //le enviamos el username y la password y que nos devuelva la autentificacion en un arrayList
                    UsernamePasswordAuthenticationToken upat= new UsernamePasswordAuthenticationToken(usuarioSingupRequestModel.getUsername(), usuarioSingupRequestModel.getPassword(), new ArrayList<>());

                    //le enviamos al manager el objeto upat y nos devuelve la autentificacion
                    Authentication authentication= authenticationManager.authenticate(upat);

                    return authentication;
                    
                } catch (IOException e) {

                    throw new RuntimeException(e); 

                }
    }

    //metodo para la generacion de token para cada uno de los usuarios logueados 
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

                //casteamos para que reconozca el user 
                String username= ((User) authResult.getPrincipal()).getUsername();

                SecretKey key= Keys.hmacShaKeyFor(Decoders.BASE64.decode(ConstantesSecurity.TOKEN_SECRETO));

                //le enviamos el username y la fecha de autentificacion y nos devuelve el token
                //seteamos la informacion
                String token= Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+ConstantesSecurity.FECHA_EXPIRACION))
                .signWith(key)
                .compact(); 

                IUsuarioService iUsuarioService= (IUsuarioService) AppContexto.getBean("usuarioService");
                UsuarioDto usuarioDto= iUsuarioService.leerUsuario(username);

                response.addHeader("Access-Control-Exponse-Headers", "Authorization. IdUsuario");
                response.addHeader("IdUsuario", usuarioDto.getIdUsuario());
                response.addHeader(ConstantesSecurity.HEADER_STRING, ConstantesSecurity.TOKEN_PREFIJO + token);

                //Recordar siempre añadir  a la configuracion de seguridad los filtros creados 


            }
    
}
