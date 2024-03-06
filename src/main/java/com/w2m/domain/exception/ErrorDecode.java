package com.w2m.domain.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = false)
public class ErrorDecode{
	private final String msg;
	private final JWTDecodeException cause;

}
