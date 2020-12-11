package com.dista.cours.controller;

import com.dista.cours.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ResponseController {
	
	protected <T> ResponseEntity<T> okAsResponseWithBody(T body) {
		return ResponseEntity.ok()
				.body(body);
	}
	
	protected <T> ResponseEntity<T> notFound() {
		throw new NotFoundException();
		
	}
	
	protected <T> ResponseEntity<T> notFound(String resourceName, Long id) {
		throw new NotFoundException(resourceName, id);
		
	}
	
	protected <T> ResponseEntity<T> badRequest() {
		return ResponseEntity.badRequest().build();
	}
	protected <T> ResponseEntity<T> returnStatus(int status) {
		return ResponseEntity.status(status).build();
	}
	protected <T> ResponseEntity<T> returnStatus(HttpStatus status) {
		return ResponseEntity.status(status).build();
	}
}
