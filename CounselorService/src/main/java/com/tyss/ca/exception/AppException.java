package com.tyss.ca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<String>("Something went wrong please try again later!!!!",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CounselorNotFoundException.class)
	public ResponseEntity<String> handleCounselorNotFoundException(CounselorNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
