package com.charlesfelipe.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charlesfelipe.workshopmongo.domain.Post;
import com.charlesfelipe.workshopmongo.repositories.PostRepository;
import com.charlesfelipe.workshopmongo.services.exception.NoSuchElementException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public Post findById(String id){
		Post obj = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Objeto não encontrado!")); 
		return obj;
	}
	
	public List<Post> findByTitle(String text){
		return repository.searchTitle(text);
	}
}
