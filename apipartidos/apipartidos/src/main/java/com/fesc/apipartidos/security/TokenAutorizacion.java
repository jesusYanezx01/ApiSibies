package com.fesc.apipartidos.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//Esta clase es para validar el token ya generado
public class TokenAutorizacion extends BasicAuthenticationFilter {

    public TokenAutorizacion(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        String header= request.getHeader(ConstantesSecurity.HEADER_STRING);

        if(header==null || !header.startsWith(ConstantesSecurity.TOKEN_PREFIJO)){
                chain.doFilter(request, response);

                return;
            }

            UsernamePasswordAuthenticationToken authenticationToken= getAuthenticationToken(header);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String header) {
        if(header!=null){
            //remplazamos el prefijo del token Bearer
            String token= header.replace(ConstantesSecurity.TOKEN_PREFIJO, "");

            SecretKey key= Keys.hmacShaKeyFor(Decoders.BASE64.decode(ConstantesSecurity.TOKEN_SECRETO));

            //compara mi clave generada con el TOKEN_SECRETO y lo compara con el token que llega, si son iguales que obtenga el cuerpo y el sujeto, y lo almacena en username
            String username= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();

            //valida que si el username es diferente de null, o sea que si encontro la similitud
            if(username!=null){
                return new UsernamePasswordAuthenticationToken(username, null,new ArrayList<>()); 

            }
        }

        return null; 
        //Recordar siempre añadir  a la configuracion de seguridad los filtros creados 
    }
    
}
