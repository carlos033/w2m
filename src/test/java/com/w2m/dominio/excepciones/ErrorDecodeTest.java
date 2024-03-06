package com.w2m.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.w2m.domain.exception.ErrorDecode;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class ErrorDecodeTest {

	@Mock
	private JWTDecodeException mockCause;

	@Test
	void testConstructorAndGetters() {
		String expectedMsg = "Error al decodificar el token";
		ErrorDecode errorDecode = new ErrorDecode(expectedMsg, mockCause);

		// Act
		String actualMsg = errorDecode.getMsg();
		JWTDecodeException actualCause = errorDecode.getCause();

		// Assert
		assertEquals(expectedMsg, actualMsg);
		assertEquals(mockCause, actualCause);
	}

	@Test
	void testErrorDecode() {
		JWTDecodeException cause = new JWTDecodeException("Test exception");
		ErrorDecode errorDecode1 = new ErrorDecode("Test message", cause);
		ErrorDecode errorDecode2 = new ErrorDecode("Test message", cause);

		// Test getters
		assertEquals("Test message", errorDecode1.getMsg());
		assertEquals(cause, errorDecode1.getCause());

		// Test equals and hashCode
		assertEquals(errorDecode1, errorDecode2);
		assertEquals(errorDecode1.hashCode(), errorDecode2.hashCode());
		ErrorDecode errorDecode3 = new ErrorDecode(" message", cause);

		// Test setters and equals with different object
		assertNotEquals(errorDecode1, errorDecode3);
	}
}
