package com.charlesfelipe.workshopmongo.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.charlesfelipe.workshopmongo.services.exception.NoSuchElementException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceHandlerException {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> noSuchElement(NoSuchElementException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
