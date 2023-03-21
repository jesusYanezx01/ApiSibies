package com.fesc.apipartidos;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) //Excluir la dependencia de seguridad
@EnableJpaAuditing //Es decir que a nivel global esta activo toda la auditoria de Jpa
public class ApipartidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApipartidosApplication.class, args);
		System.out.println("api Corriendo...");
	}

	@Bean //Anotacion para especificar que es bean
	public ModelMapper modelMapper(){ //metodo para el mapeo de modelos y utilizarlo en el proyecto 

		//Lo instanciamos 
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); //que compare estrictamente con el nombre del atributo y su tipo
		return modelMapper;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){ //metodo para la codificacion de la clave encriptada
		return new BCryptPasswordEncoder();
	}

}
