package com.example.demo.Exception;

import org.springframework.http.HttpStatus;

public class AssignmentServiceException extends RuntimeException {
	private String errorMessage;
	private HttpStatus httpStatus;

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public AssignmentServiceException(String errorMessage, HttpStatus httpStatus) {
		super();
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

}
