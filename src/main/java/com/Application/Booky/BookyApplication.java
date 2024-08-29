package com.Application.Booky;

import com.Application.Booky.entity.role;
import com.Application.Booky.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")

public class BookyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookyApplication.class, args);
	}



@Bean

	public CommandLineRunner runner(RoleRepository roleRepository){

		return args -> {

			if (roleRepository.findByname("USER").isEmpty() ){
				roleRepository.save(role.builder().name("USER").build());
			}



		};


}



}
