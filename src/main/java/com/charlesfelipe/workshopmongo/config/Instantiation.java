package com.charlesfelipe.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.charlesfelipe.workshopmongo.domain.User;
import com.charlesfelipe.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User charles = new User(null, "Charles Felipe", "charles@gmail.com");
		User vitoria = new User(null, "Vitória Silva", "vitoria@gmail.com");
		User jessie = new User(null, "Jessie Ferreira", "jessie@gmail.com");
		
		userRepository.saveAll(Arrays.asList(charles, vitoria, jessie));
		
	}
}
