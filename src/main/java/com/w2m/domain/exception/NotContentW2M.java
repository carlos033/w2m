package com.w2m.domain.exception;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NotContentW2M extends RuntimeException{

	private static final long serialVersionUID = 2L;
	private final HttpStatus code;
	private final String warning;

}
