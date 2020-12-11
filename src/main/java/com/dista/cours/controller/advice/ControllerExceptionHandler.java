package com.dista.cours.controller.advice;

import com.dista.cours.exception.AuthenticationException;
import com.dista.cours.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<ErrorWrapper> handleOthers(Exception e) {
		logger.info("", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ErrorEnum.INTERNAL_SERVER_ERROR.getErrorWrapper());
	}
	
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	public ResponseEntity<ErrorWrapper> handleOthers(DataIntegrityViolationException e) {
		logger.debug(e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorEnum.DATA_INTEGRITY_ERROR.getErrorWrapper());
	}
	
	@ExceptionHandler(value = {NotFoundException.class})
	public ResponseEntity<ErrorWrapper> notFound(NotFoundException e) {
		logger.debug(e);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ErrorEnum.NOT_FOUND.getErrorWrapper()
						.withMessage(e.getMessage()));
	}
	
	@ExceptionHandler(value = {AuthenticationException.class})
	public ResponseEntity<ErrorWrapper> authError(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ErrorEnum.UNAUTHORIZED.getErrorWrapper()
						.withMessage(e.getMessage()));
	}
}
