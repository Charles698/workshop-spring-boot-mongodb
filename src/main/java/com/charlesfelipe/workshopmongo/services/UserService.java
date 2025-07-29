package com.charlesfelipe.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charlesfelipe.workshopmongo.domain.User;
import com.charlesfelipe.workshopmongo.dto.UserDTO;
import com.charlesfelipe.workshopmongo.repositories.UserRepository;
import com.charlesfelipe.workshopmongo.services.exception.NoSuchElementException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id){
		User obj = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Objeto não encontrado!")); 
		return obj;
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User fromDto(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
