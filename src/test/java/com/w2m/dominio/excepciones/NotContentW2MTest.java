package com.w2m.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.w2m.domain.exception.NotContentW2M;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class NotContentW2MTest{
	@Test
	void testNotContentW2MException() {
		String mensaje = "Recurso no encontrado";

		NotContentW2M exception = assertThrows(NotContentW2M.class, () -> {
			throw new NotContentW2M(HttpStatus.NO_CONTENT, mensaje);
		});

		assertEquals(mensaje, exception.getWarning());
	}

	@Test
	void testEquals() {
		NotContentW2M instance1 = new NotContentW2M(HttpStatus.NO_CONTENT, "Not content");
		NotContentW2M instance2 = new NotContentW2M(HttpStatus.NO_CONTENT, "Not content");

		assertEquals(instance1, instance2);

		NotContentW2M instance3 = new NotContentW2M(HttpStatus.BAD_GATEWAY, "Not content");
		assertNotEquals(instance1, instance3);
	}

	@Test
	void testHashCode() {
		NotContentW2M instance1 = new NotContentW2M(HttpStatus.NO_CONTENT, "Not content");
		NotContentW2M instance2 = new NotContentW2M(HttpStatus.NO_CONTENT, "Not content");

		assertEquals(instance1.hashCode(), instance2.hashCode());
	}

}
