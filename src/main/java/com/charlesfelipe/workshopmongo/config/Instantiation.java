package com.charlesfelipe.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.charlesfelipe.workshopmongo.domain.Post;
import com.charlesfelipe.workshopmongo.domain.User;
import com.charlesfelipe.workshopmongo.repositories.PostRepository;
import com.charlesfelipe.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User charles = new User(null, "Charles Felipe", "charles@gmail.com");
		User henrique = new User(null, "Henrique Santos", "henrique@gmail.com");
		User joao = new User(null, "João Ferreira", "joao@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("28/07/2025"), "Patiu férias!", "Vou viajar para o Rio de Janeiro", henrique);
		Post post2 = new Post(null, sdf.parse("05/08/2025"), "Bom dia!", "Bora estudar.", joao);
		
		userRepository.saveAll(Arrays.asList(charles, henrique, joao));
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
}
