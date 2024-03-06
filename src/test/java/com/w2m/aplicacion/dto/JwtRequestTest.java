package com.w2m.aplicacion.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class JwtRequestTest{

	@InjectMocks
	private JwtRequest jwtRequest;

	@Test
	void testIdentificador() {
		String identificator = "testIdentificador";
		jwtRequest.setIdentificator(identificator);
		assertEquals(identificator, jwtRequest.getIdentificator());
	}

	@Test
	void testPassword() {
		String password = "testPassword";
		jwtRequest.setPassword(password);
		assertEquals(password, jwtRequest.getPassword());
	}

	@Test
	void testEquals() {
		JwtRequest jwtRequest1 = new JwtRequest("identificator1", "password1");
		JwtRequest jwtRequest2 = new JwtRequest("identificator1", "password1");
		assertEquals(jwtRequest1, jwtRequest2);
	}

	@Test
	void testHashCode() {
		JwtRequest jwtRequest1 = new JwtRequest("identificator1", "password1");
		JwtRequest jwtRequest2 = new JwtRequest("identificator1", "password1");
		assertEquals(jwtRequest1.hashCode(), jwtRequest2.hashCode());
	}

	@Test
	void testToString() {
		String expected =
				"class JwtRequest {\n    identificator: identificator1\n    password: password1\n}";
		JwtRequest jwtRequest =
				JwtRequest.builder().identificator("identificator1").password("password1").build();
		assertEquals(expected, jwtRequest.toString());
	}


	@Test
	void testNoArgsConstructor() {
		JwtRequest jwtRequest = JwtRequest.builder().build();
		assertNull(jwtRequest.getIdentificator());
		assertNull(jwtRequest.getPassword());
	}

}
