package com.cts.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {
	Object data;
	String message;
	String errorMessage;
	String details;
	HttpStatus status;
	int statusCode;

	public enum Status {
		OK, FAILED, AUTH_FAILED
	}

	public Response() {

	}

	public Response(int value, String message) {
		super();
		this.message = message;
		this.statusCode = value;
	}

	public Response(Object data, String message, String errorMessage, String details, HttpStatus status) {
		super();
		this.data = data;
		this.message = message;
		this.errorMessage = errorMessage;
		this.details = details;
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
