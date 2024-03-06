package com.w2m.presentacion.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.w2m.domain.exception.DatabaseError;
import com.w2m.domain.exception.ErrorResponse;
import com.w2m.domain.exception.InvalidClaimTypeException;
import com.w2m.domain.exception.NotContentW2M;
import com.w2m.presentation.AutenticacionController;
import com.w2m.presentation.ControllerErrors;

@ExtendWith(MockitoExtension.class)
class ControladorErroresTest{

	@InjectMocks
	private ControllerErrors controladorErrores;

	@Mock
	private MethodArgumentNotValidException methodArgumentNotValidException;
	@Mock
	private AutenticacionController autenticacionController;

	@Test
	void testItemNotFoundHandler() {
		NotContentW2M excepcion = new NotContentW2M(HttpStatus.NO_CONTENT, "Not content");
		ResponseEntity<Void> responseEntity = controladorErrores.itemNotFoundHandler(excepcion);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		assertNull(responseEntity.getBody());
	}

	@Test
	void testHandleMethodArgumentNotValid() {
		MethodArgumentNotValidException methodArgumentNotValidException =
				mock(MethodArgumentNotValidException.class);

		BindingResult bindingResult = mock(BindingResult.class);

		when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);

		ResponseEntity<ErrorResponse> result =
				controladorErrores.handleMethodArgumentNotValid(methodArgumentNotValidException);

		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		ErrorResponse errorResponse = result.getBody();
		assertEquals("Failed validation", errorResponse.getMessage());
	}

	@Test
	void testHandleMethoInternalError() {
		DatabaseError ex = new DatabaseError(500, "error");
		ResponseEntity<Map<String, Object>> response = controladorErrores.handleMethoInternalError(ex);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		Map<String, Object> responseBody = response.getBody();
		assertEquals(500, responseBody.get("status"));
		assertEquals("Internal Server Error", responseBody.get("error"));
		assertEquals("An internal error has occurred. Please contact the administrator.",
				responseBody.get("message"));
		assertEquals(LocalDateTime.class, responseBody.get("timestamp").getClass());
	}

	@Test
	void testHandleJWTDecodeException() {
		JWTDecodeException exception = new JWTDecodeException("Test error");
		ResponseEntity<Object> response = controladorErrores.handleJWTDecodeException(exception);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertEquals("Error found: Test error".trim(), response.getBody().toString().trim());
	}

	@Test
	void testHandleInvalidClaimTypeException() {
		InvalidClaimTypeException exception = new InvalidClaimTypeException("Test error");
		ResponseEntity<Object> response = controladorErrores.handleInvalidClaimTypeException(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Error found: Test error".trim(), response.getBody().toString().trim());
	}



	@Test
	void testHandleIOException() {
		IOException exception = new IOException("Test I/O error");
		ResponseEntity<String> response = controladorErrores.handleIOException(exception);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("An I/O error occurred: Test I/O error", response.getBody());
	}
}
