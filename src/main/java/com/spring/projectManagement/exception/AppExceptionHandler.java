package com.spring.projectManagement.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<ExceptionModel> hundlerException(GlobalException globalException, WebRequest request) {
		ExceptionModel exceptionModel = new ExceptionModel(request.getDescription(false),globalException.getLocalizedMessage());
		return new ResponseEntity<ExceptionModel>(exceptionModel, globalException.getCodeStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> listOfError = ex.getBindingResult()
									 .getFieldErrors()
									 .stream()
									 .map(value -> value.getDefaultMessage())
									 .collect(Collectors.toList());
		
		List<String> errors = ex.getBindingResult()
								.getGlobalErrors()
								.stream()
								.map(value -> value.getDefaultMessage())
								.collect(Collectors.toList());
		
		listOfError.addAll(errors);
				
		return new ResponseEntity<Object>(listOfError,HttpStatus.BAD_REQUEST);
	}
}