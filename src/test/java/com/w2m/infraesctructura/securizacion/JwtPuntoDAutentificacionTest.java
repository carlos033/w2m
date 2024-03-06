package com.w2m.infraesctructura.securizacion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;

import com.w2m.infrastructure.securization.JwtAuthenticationPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class JwtPuntoDAutentificacionTest {

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private AuthenticationException authException;

	@InjectMocks
	private JwtAuthenticationPoint jwtAuthenticationPoint;

	@Test
	void testCommence() throws IOException {
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);

		jwtAuthenticationPoint.commence(request, response, authException);

		verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
		verify(response).setContentType(MediaType.APPLICATION_JSON_VALUE);
	}
}