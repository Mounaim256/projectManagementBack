package com.spring.projectManagement.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends GlobalException{

	private static final long serialVersionUID = 1L;

	public ConflictException(String msg) {
		super(msg);
	}

	@Override
	HttpStatus getCodeStatus() {
		return HttpStatus.CONFLICT;
	}

}
