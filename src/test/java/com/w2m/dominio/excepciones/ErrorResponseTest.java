package com.w2m.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.w2m.domain.exception.ErrorResponse;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class ErrorResponseTest {
	@Test
	void testConstructorAndGetters() {
		String mensagge = "Error";
		List<String> details = Arrays.asList("detail 1", "detail 2");

		ErrorResponse errorResponse = new ErrorResponse(mensagge, details);

		assertEquals(mensagge, errorResponse.getMessage());
		assertEquals(details, errorResponse.getDetails());
	}

	@Test
	void testSetterAndGetters() {
		ErrorResponse errorResponse = new ErrorResponse();

		String mensaje = "new mensagge error";
		List<String> detalles = Arrays.asList("new detail 1", "New detail  2");

		errorResponse.setMessage(mensaje);
		errorResponse.setDetails(detalles);

		assertEquals(mensaje, errorResponse.getMessage());
		assertEquals(detalles, errorResponse.getDetails());
	}

	@Test
	void testNoArgsConstructor() {
		ErrorResponse errorResponse = new ErrorResponse();

		assertNotNull(errorResponse);
	}

	@Test
	void testEqualsAndHashCode() {
		ErrorResponse firstResponse = new ErrorResponse("Mensagge 1", Arrays.asList("Detail A", "Detail B"));
		ErrorResponse secondResponse = new ErrorResponse("Mensagge 1", Arrays.asList("Detail A", "Detail B"));

		assertEquals(firstResponse, secondResponse);
		assertEquals(firstResponse.hashCode(), secondResponse.hashCode());
	}
}
