package com.cts.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.exception.BookNotFoundException;
import com.cts.exception.CartNotFoundException;
import com.cts.exception.InvalidBookException;
import com.cts.exception.InvalidCartException;
import com.cts.exception.InvalidOrderException;
import com.cts.exception.OrderNotFoundException;
import com.cts.exception.UserAlreadyExistsException;
import com.cts.exception.UserDetailsNotFoundException;
import com.cts.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// User Exceptions
	
	@ExceptionHandler(UserDetailsNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Response handleUserDetailsNotFoundException(UserDetailsNotFoundException ex) {
		Response response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		response.setErrorMessage("User not found");
		return response;
	}

	@ExceptionHandler({ UserAlreadyExistsException.class })
	public Response handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		Response response = new Response(HttpStatus.CONFLICT.value(), ex.getMessage());
		response.setErrorMessage("User already exists");
		return response;
	}

	// Book Exceptions

	@ExceptionHandler(BookNotFoundException.class)
	public Response handleBookNotFoundException(BookNotFoundException ex) {
		Response response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		response.setErrorMessage("Book not found");
		return response;
	}

	@ExceptionHandler(InvalidBookException.class)
	public Response handleInvalidBookException(InvalidBookException ex) {
		Response response = new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		response.setErrorMessage("Invalid book");
		return response;
	}

	// Order Exceptions

	@ExceptionHandler(OrderNotFoundException.class)
	public Response handleOrderNotFoundException(OrderNotFoundException ex) {
		Response response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		response.setErrorMessage("Order not found");
		return response;
	}

	@ExceptionHandler(InvalidOrderException.class)
	public Response handleInvalidOrderException(InvalidOrderException ex) {
		Response response = new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		response.setErrorMessage("Invalid order");
		return response;
	}

	// Cart Exceptions

	@ExceptionHandler(CartNotFoundException.class)
	public Response handleCartNotFoundException(CartNotFoundException ex) {
		Response response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		response.setErrorMessage("Cart not found");
		return response;
	}

	@ExceptionHandler(InvalidCartException.class)
	public Response handleInvalidCartException(InvalidCartException ex) {
		Response response = new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		response.setErrorMessage("Invalid cart");
		return response;
	}

	// Global Exception Handler

	@ExceptionHandler(Exception.class)
	public Response handleException(Exception ex) {
		Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		response.setErrorMessage("Internal server error");
		return response;
	}
}
