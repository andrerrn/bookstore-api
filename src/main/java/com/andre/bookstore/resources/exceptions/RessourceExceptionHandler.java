package com.andre.bookstore.resources.exceptions;


import java.util.Iterator;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<EstandardError> validationError(MethodArgumentNotValidException e, ServletRequest request) {
		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Error na validação dos campos");
		
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			error.addErrors(x.getField(),x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
