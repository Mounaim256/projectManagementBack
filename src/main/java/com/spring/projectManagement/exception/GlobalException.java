package com.spring.projectManagement.exception;

import org.springframework.http.HttpStatus;

abstract class GlobalException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GlobalException(String msg) {
		super(msg);
	}

	abstract HttpStatus getCodeStatus();

}
