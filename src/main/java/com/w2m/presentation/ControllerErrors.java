package com.w2m.presentation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.w2m.domain.exception.DatabaseError;
import com.w2m.domain.exception.ErrorResponse;
import com.w2m.domain.exception.InvalidClaimTypeException;
import com.w2m.domain.exception.NotContentW2M;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerErrors {
	private static final String ERROR_FOUND = " Error found: ";

	@ExceptionHandler(NotContentW2M.class)
	public ResponseEntity<Void> itemNotFoundHandler(NotContentW2M ex) {
		log.error("Error 204: {}", ex.getWarning());
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		log.error("Error 400: {}", ex.getMessage());

		List<String> details = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.toList();

		ErrorResponse error = new ErrorResponse("Failed validation", details);
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(DatabaseError.class)
	public ResponseEntity<Map<String, Object>> handleMethoInternalError(DatabaseError ex) {
		log.error("Error 500: {}", ex.getMessage());

		Map<String, Object> body = Map.of("status", 500, "error", "Internal Server Error", "timestamp",
				LocalDateTime.now(), "message", "An internal error has occurred. Please contact the administrator.");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}

	@ExceptionHandler(value = JWTDecodeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleJWTDecodeException(JWTDecodeException exception) {
		return new ResponseEntity<>(ERROR_FOUND + exception.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = InvalidClaimTypeException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> handleInvalidClaimTypeException(InvalidClaimTypeException exception) {
		return new ResponseEntity<>(ERROR_FOUND + exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOException(IOException ex) {
		String messageError = "An I/O error occurred: " + ex.getMessage();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageError);
	}
}
