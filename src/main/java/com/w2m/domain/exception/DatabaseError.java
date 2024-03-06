package com.w2m.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DatabaseError extends InternalError{

	private static final long serialVersionUID = 1L;
	private final int code;

	public DatabaseError(int codigoError, String msg) {
		super(msg);
		this.code = codigoError;
	}
}
