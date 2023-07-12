package com.cts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserDetailsNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserDetailsNotFoundException(String message) {
		super(message);              // super(message) is calling the constructor of super class  
	}
}