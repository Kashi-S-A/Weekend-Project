package com.tyss.ca.exception;

public class CounselorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public CounselorNotFoundException(String message) {
		this.message = message;
	}

	public CounselorNotFoundException() {
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
