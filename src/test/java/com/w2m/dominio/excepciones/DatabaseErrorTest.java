package com.w2m.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.w2m.domain.exception.DatabaseError;

@ExtendWith(MockitoExtension.class)
class DatabaseErrorTest {
	@Test
	void testDatabaseErrorException() {
		int codigoError = 500;
		String mensaje = "Error de base de datos";

		DatabaseError exception = assertThrows(DatabaseError.class, () -> {
			throw new DatabaseError(codigoError, mensaje);
		});

		assertEquals(mensaje, exception.getMessage());
		assertEquals(codigoError, exception.getCode());
	}

	@Test
	void testEquals() {
		DatabaseError instance1 = new DatabaseError(500, "Internal Server Error");
		DatabaseError instance2 = new DatabaseError(500, "Internal Server Error");

		assertEquals(instance1, instance2);

		DatabaseError instance3 = new DatabaseError(501, "Internal Server Error");
		assertNotEquals(instance1, instance3);
	}

	@Test
	void testHashCode() {
		DatabaseError instance1 = new DatabaseError(500, "Internal Server Error");
		DatabaseError instance2 = new DatabaseError(500, "Internal Server Error");

		assertEquals(instance1.hashCode(), instance2.hashCode());
	}
}
