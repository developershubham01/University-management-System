package com.example.demo.Exception;

import org.springframework.http.HttpStatus;

public class ProfessorServiceException extends RuntimeException {
	private String errorMessage;
	private HttpStatus httpStatus;

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ProfessorServiceException(String errorMessage, HttpStatus httpStatus) {
		super();
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

}
