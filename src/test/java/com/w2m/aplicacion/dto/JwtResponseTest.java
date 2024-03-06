package com.w2m.aplicacion.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JwtResponseTest{

	@InjectMocks
	private JwtResponse jwtResponse;

	@Test
	void testNoArgsConstructor() {
		JwtResponse jwtResponse = JwtResponse.builder().build();
		assertEquals(JwtResponse.class, jwtResponse.getClass());
	}

	@Test
	void testAllArgsConstructor() {
		String expectedJwttoken = "token";
		JwtResponse jwtResponse = new JwtResponse(expectedJwttoken);
		assertEquals(expectedJwttoken, jwtResponse.getJwttoken(),
				"El jwttoken no coincide con el esperado");
	}

	@Test
	void testGetSetJwttoken() {
		String expectedJwttoken = "token";
		jwtResponse.setJwttoken(expectedJwttoken);
		String actualJwttoken = jwtResponse.getJwttoken();
		assertEquals(expectedJwttoken, actualJwttoken, "El jwttoken no coincide con el esperado");
	}

	@Test
	void testEquals() {
		JwtResponse jwtResponse1 = new JwtResponse("token1");
		JwtResponse jwtResponse2 = new JwtResponse("token1");
		assertEquals(jwtResponse1, jwtResponse2, "Los objetos JwtResponse deberían ser iguales");

		jwtResponse2 = new JwtResponse("token2");
		assertNotEquals(jwtResponse1, jwtResponse2, "Los objetos JwtResponse no deberían ser iguales");
	}

	@Test
	void testHashCode() {
		JwtResponse jwtResponse1 = new JwtResponse("token1");
		JwtResponse jwtResponse2 = new JwtResponse("token1");
		assertEquals(jwtResponse1.hashCode(), jwtResponse2.hashCode(),
				"Los hashcodes deberían ser iguales");

		jwtResponse2 = new JwtResponse("token2");
		assertNotEquals(jwtResponse1.hashCode(), jwtResponse2.hashCode(),
				"Los hashcodes no deberían ser iguales");
	}

	@Test
	void testToString() {
		String expectedString = "class JwtResponse {\n    jwttoken: token\n}";
		JwtResponse jwtResponse = new JwtResponse("token");
		assertEquals(expectedString, jwtResponse.toString(),
				"La representación en cadena no coincide con la esperada");
	}
}
