package com.w2m.infraesctructura.securizacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.w2m.infrastructure.entity.Administrator;
import com.w2m.infrastructure.securization.JwtToken;

@ExtendWith(MockitoExtension.class)
class JwtTokenTest {

	@InjectMocks
	private JwtToken jwtToken;

	@Test
	void testObtenerIdentificadorDelToken() {
		Administrator user = new Administrator("Pedro69", "Pedro", "1234");
		String token = jwtToken.generateToken(user);
		String actualIdentifier = jwtToken.getTokenIdentifier(token);
		assertEquals(user.getIdentifier(), actualIdentifier, "The identifier does not match the expected one");
	}

	@Test
	void testObtenerVencimientoDelToken() {
		Administrator user = new Administrator("Pedro69", "Pedro", "1234");
		String token = jwtToken.generateToken(user);
		Instant expiration = jwtToken.getTokenExpiration(token);
		assertTrue(expiration.isAfter(Instant.now()), "The token has already expired");
	}

	@Test
	void testGenerarToken() {
		Administrator user = new Administrator("Pedro69", "Pedro", "1234");
		String token = jwtToken.generateToken(user);
		String actualIdentifier = jwtToken.getTokenIdentifier(token);
		assertEquals(user.getIdentifier(), actualIdentifier, "The identifier does not match the expected one");
	}

	@Test
	void testValidateToken() {
		Administrator user = new Administrator("Pedro69", "Pedro", "1234");
		String token = jwtToken.generateToken(user);
		Boolean isValid = jwtToken.validateToken(token, user.getIdentifier());
		assertTrue(isValid, "The token is invalid");
	}

	@Test
	void testDecodeTokenException() {
		String invalidToken = "Invalid Token";
		assertThrows(JWTDecodeException.class, () -> jwtToken.getTokenIdentifier(invalidToken),
				"Error decoding the token");
	}

}
