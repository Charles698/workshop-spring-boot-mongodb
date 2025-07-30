package com.charlesfelipe.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.charlesfelipe.workshopmongo.domain.Post;
import com.charlesfelipe.workshopmongo.domain.User;
import com.charlesfelipe.workshopmongo.dto.AuthorDTO;
import com.charlesfelipe.workshopmongo.dto.CommentDTO;
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
		
		userRepository.saveAll(Arrays.asList(charles, henrique, joao));
		
		Post post1 = new Post(null, sdf.parse("28/07/2025"), "Partiu ferias!", "Vou viajar para o Rio de Janeiro", new AuthorDTO(henrique));
		Post post2 = new Post(null, sdf.parse("05/08/2025"), "Bom dia!", "Bora estudar.", new AuthorDTO(joao));
		
		CommentDTO c1 = new CommentDTO("Boa viagem, Henrique", sdf.parse("28/07/2025"), new AuthorDTO(charles));
		CommentDTO c2 = new CommentDTO("Aproveite o RJ.", sdf.parse("30/07/2025"), new AuthorDTO(joao));
		CommentDTO c3 = new CommentDTO("Isso, bora.", sdf.parse("05/08/2025"), new AuthorDTO(henrique));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		henrique.getPosts().add(post1);
		joao.getPosts().add(post2);
		
		userRepository.saveAll(Arrays.asList(henrique, joao));
	}
}
