package com.dista.cours.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileStorageException extends RuntimeException {
	private String fileName;
	
	public FileStorageException() {
		super();
	}
	
	public FileStorageException(String fileName) {
		super("The file '" + fileName + "' cannot be uploaded");
		this.fileName= fileName;
	}
	
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
		this.fileName = message;
	}
}
