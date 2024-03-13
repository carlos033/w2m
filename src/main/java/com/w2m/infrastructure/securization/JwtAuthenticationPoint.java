package com.w2m.infrastructure.securization;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@Component
public class JwtAuthenticationPoint implements AuthenticationEntryPoint{
	private static final String UNAUTHORIZED_MESSAGE = "{\"message\": \"Not authorized\"}";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(UNAUTHORIZED_MESSAGE);
	}
}
