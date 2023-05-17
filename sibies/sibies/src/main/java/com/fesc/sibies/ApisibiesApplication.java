package com.fesc.sibies;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fesc.sibies.utils.AppContexto;




@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) 
@EnableJpaAuditing 
public class ApisibiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApisibiesApplication.class, args);
		System.out.println("==============================");
		System.out.println("Api corriendo...");
		System.out.println("==============================");

		
	}

	@Bean 
	public ModelMapper modelMapper(){  

		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); 
		return modelMapper;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){ 
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AppContexto appContexto(){
		return new AppContexto();
	}

}
