package com.dista.cours.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {
	private String resourceName;
	
	
	public AuthenticationException(String reason) {
		this.resourceName = reason;
	}
	
	@Override
	public String getMessage() {
		return String.format("A problem occurred while trying to authenticate : %s", resourceName);
	}
}
