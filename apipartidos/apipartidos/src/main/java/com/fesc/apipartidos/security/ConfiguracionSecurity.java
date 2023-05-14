package com.fesc.apipartidos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fesc.apipartidos.Services.IUsuarioService;

//Acá estara toda la configuracion de seguridad de nuestra aplicacion
//Con la anotacion @EnableWebSecurity activamos la seguridad en la web
@EnableWebSecurity
//esto es para el tema de puesta en produccion cors
@Configuration
public class ConfiguracionSecurity extends WebSecurityConfigurerAdapter implements WebMvcConfigurer   {

    private final IUsuarioService iUsuarioService; 
    private final BCryptPasswordEncoder bCryptPasswordEncoder; 


    public ConfiguracionSecurity(IUsuarioService iUsuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.iUsuarioService = iUsuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        //activamos los cors
        .cors()
        //agregamos un and para que reciba las otras autorizaciones
        .and()
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
        //.addFilter(new UsuarioAutenticacion(authenticationManager()))
        .addFilter(getUsuarioAutenticacion())
        .addFilter(new TokenAutorizacion(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(iUsuarioService).passwordEncoder(bCryptPasswordEncoder);
    }

    public UsuarioAutenticacion getUsuarioAutenticacion() throws Exception{
        final UsuarioAutenticacion usuarioAutenticacion= new UsuarioAutenticacion(authenticationManager());

        usuarioAutenticacion.setFilterProcessesUrl("/usuario/login");

        return usuarioAutenticacion;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //especificar donde iran esos ingresos 
        //esto quiere decir que es para cualquiera "/**"
        registry.addMapping("/**")
        //en el tema de los metodos le digo que me reciba todos los metodos "*"
        .allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    }

   

    
    

    
}
