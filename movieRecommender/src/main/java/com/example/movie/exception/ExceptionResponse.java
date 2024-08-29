package com.example.movie.exception;

public class ExceptionResponse {
	
	private String message;
	private String status;
	private int statusCode;
	
	public ExceptionResponse(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	
	public ExceptionResponse(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
