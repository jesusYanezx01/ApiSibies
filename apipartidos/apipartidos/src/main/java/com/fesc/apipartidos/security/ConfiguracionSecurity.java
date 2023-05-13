package com.fesc.apipartidos.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fesc.apipartidos.Services.IUsuarioService;

//Acá estara toda la configuracion de seguridad de nuestra aplicacion
//Con la anotacion @EnableWebSecurity activamos la seguridad en la web
@EnableWebSecurity
public class ConfiguracionSecurity extends WebSecurityConfigurerAdapter    {

    private final IUsuarioService iUsuarioService; 
    private final BCryptPasswordEncoder bCryptPasswordEncoder; 


    public ConfiguracionSecurity(IUsuarioService iUsuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.iUsuarioService = iUsuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        //endpoints que cualquier persona puede utilizar
        .antMatchers(HttpMethod.POST, "/usuario").permitAll()
        .antMatchers(HttpMethod.GET, "/partido").permitAll()
        .antMatchers(HttpMethod.GET, "/partido/{id}").permitAll()
        //resto de endpoints los usuarios deben autentificarse 
        .anyRequest().authenticated()
        .and()
        //Recordar siempre añadir  a la configuracion de seguridad los filtros creados
        .addFilter(new UsuarioAutenticacion(authenticationManager()))
        .addFilter(new TokenAutorizacion(authenticationManager()));

        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(iUsuarioService).passwordEncoder(bCryptPasswordEncoder);
    }

   

    
    

    
}
