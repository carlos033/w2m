package com.w2m.domain.exception;

public class InvalidClaimTypeException extends RuntimeException{
	private static final long serialVersionUID = 3L;

	public InvalidClaimTypeException(String message) {
		super(message);
	}
}
