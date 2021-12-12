package com.andre.bookstore.resources.exceptions;


import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.andre.bookstore.service.exceptions.DataIntegrityViolationException;
import com.andre.bookstore.service.exceptions.ObjectNotFoundException;



@ControllerAdvice
public class RessourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<EstandardError> objectNotFoundExcepcion(ObjectNotFoundException e, ServletRequest request) {
		EstandardError error = new EstandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<EstandardError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request) {
		EstandardError error = new EstandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
