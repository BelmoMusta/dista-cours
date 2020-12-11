package com.dista.cours.controller.advice;

import lombok.Getter;

@Getter
public enum ErrorEnum {
	NOT_FOUND(404, "The requested content is not found"),
	BAD_REQUEST(400, "Bad request"),
	UNAUTHORIZED(401, "unauthorized"),
	INTERNAL_SERVER_ERROR(500, "A server side error has occurred"),
	// customized errors,
	DATA_INTEGRITY_ERROR(9000, "data integrity error");
	private final ErrorWrapper errorWrapper;
	
	ErrorEnum(int code, String message) {
		errorWrapper = new ErrorWrapper(code, message);
	}
}
