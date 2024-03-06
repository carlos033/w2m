package com.w2m.aplicacion.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import com.w2m.aplicacion.dto.JwtRequest;
import com.w2m.aplicacion.dto.JwtResponse;
import com.w2m.presentacion.api.AutenticacionApi;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class AutenticacionApiTest {

	private final AutenticacionApi autenticacionApi = new AutenticacionApi() {
		@Override
		public Optional<NativeWebRequest> getRequest() {
			return Optional.empty();
		}
	};

	@Test
	void getRequestReturnsEmptyOptional() {
		Optional<NativeWebRequest> request = autenticacionApi.getRequest();
		assertTrue(request.isEmpty());
	}

	@Test
	void autenticacionLoginPostReturnsNotImplemented() {
		JwtRequest jwtRequest = JwtRequest.builder().build();

		ResponseEntity<JwtResponse> response = autenticacionApi.autenticacionLoginPost(jwtRequest);

		assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
	}
}
