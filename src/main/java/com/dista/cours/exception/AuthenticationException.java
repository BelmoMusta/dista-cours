package com.dista.cours.exception;

import com.dista.cours.i18n.MessageProvider;
import com.dista.cours.i18n.MessagesKeys;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {
	
	private String reason;
	
	
	public AuthenticationException(String reason) {
		this.reason = reason;
	}
	public AuthenticationException(String reason, String message) {
		super(message);
		this.reason = reason;
	}
	
	@Override
	public String getMessage() {
		return MessageProvider.get().getMessage(MessagesKeys.AUTH_ERROR.getValue(),
				MessageProvider.get().getMessage(reason, super.getMessage()));
	}
}
