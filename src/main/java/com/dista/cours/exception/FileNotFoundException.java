package com.dista.cours.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileNotFoundException extends NotFoundException {
	
	public FileNotFoundException(String resourceName, Object id) {
		super(resourceName, id);
	}
}
