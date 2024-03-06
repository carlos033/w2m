package com.w2m.presentacion.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.w2m.aplicacion.dto.JwtRequest;
import com.w2m.aplicacion.dto.JwtResponse;
import com.w2m.application.service.AdministratorService;
import com.w2m.infrastructure.securization.JwtToken;
import com.w2m.presentation.AutenticacionController;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class AutenticacionControllerTest{

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtToken jwtToken;

	@Mock
	private AdministratorService servicioUsuario;

	@InjectMocks
	private AutenticacionController autenticacionController;

	@Test
	void testAutenticacionLoginPost() {
		JwtRequest jwtRequest = JwtRequest.builder().build();
		jwtRequest.setIdentificator("testUser");
		jwtRequest.setPassword("testPassword");

		when(jwtToken.generateToken(any())).thenReturn("testToken");

		ResponseEntity<JwtResponse> response =
				autenticacionController.autenticacionLoginPost(jwtRequest);

		verify(authenticationManager, times(1))
				.authenticate(any(UsernamePasswordAuthenticationToken.class));
		verify(jwtToken, times(1)).generateToken(any());

		assertEquals("testToken", response.getBody().getJwttoken());
	}
}
