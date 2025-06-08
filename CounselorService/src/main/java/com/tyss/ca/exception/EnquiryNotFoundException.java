package com.tyss.ca.exception;

public class EnquiryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnquiryNotFoundException(String message) {
		super(message);
	}

	public EnquiryNotFoundException() {
	}
}
