package com.cts.exception;

 public class OrderNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}