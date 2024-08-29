package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(final ResourceNotFoundException e) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return handleErrors(e.getMessage(), status);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidCredentialsException(final InvalidCredentialsException e) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return handleErrors(e.getMessage(), status);
	}
	
	@ExceptionHandler(InvalidRatingException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRatingException(final InvalidRatingException e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return handleErrors(e.getMessage(), status);
    }
	@ExceptionHandler(RequestDataException.class)
	public ResponseEntity<ExceptionResponse> handleRequestDataException(final RequestDataException e) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		return handleErrors(e.getMessage(), status);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<?> handleInvalidInputException(final InvalidInputException e) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		return handleErrors(e.getMessage(), status);
	}
	private ResponseEntity<ExceptionResponse> handleErrors(String message, HttpStatus status) {
		ExceptionResponse errorResponse = new ExceptionResponse(message, status.value());
        return new ResponseEntity<>(errorResponse, status);
    }

}
