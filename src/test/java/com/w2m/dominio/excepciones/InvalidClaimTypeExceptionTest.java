package com.w2m.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.w2m.domain.exception.InvalidClaimTypeException;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class InvalidClaimTypeExceptionTest {
	@Test
	void testInvalidClaimTypeException() {
		String expectedMessage = "Error encontrado";
		InvalidClaimTypeException exception = new InvalidClaimTypeException(expectedMessage);

		assertEquals(expectedMessage, exception.getMessage());
	}
}
