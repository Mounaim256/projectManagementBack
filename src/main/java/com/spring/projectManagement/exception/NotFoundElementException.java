package com.spring.projectManagement.exception;

import org.springframework.http.HttpStatus;

public class NotFoundElementException extends GlobalException{

	private static final long serialVersionUID = 1L;

	public NotFoundElementException(String msg) {
		super(msg);
	}

	@Override
	HttpStatus getCodeStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
