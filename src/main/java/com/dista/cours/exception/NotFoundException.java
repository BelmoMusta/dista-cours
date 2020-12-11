package com.dista.cours.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private String resourceName;
	private Object id;
	
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String resourceName, Object id) {
		super();
		this.resourceName = resourceName;
		this.id = id;
		
	}
	
	@Override
	public String getMessage() {
		
		return String.format("The resource '%s' identified by '%s' is not found ", resourceName, id);
	}
}
