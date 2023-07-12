package com.cts.exception;

public class InvalidOrderException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;   //The serialVersionUID acts as a version identifier for the serialized object. If the serialVersionUID of the deserialized object does not match the serialVersionUID of the class in the current version, an InvalidClassException will be thrown, indicating a version mismatch.

	public  InvalidOrderException(String message) {
        super(message);
    }
}
