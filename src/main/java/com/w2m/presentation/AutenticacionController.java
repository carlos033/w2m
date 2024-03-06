package com.w2m.presentation;

import java.util.Collection;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.w2m.aplicacion.dto.JwtRequest;
import com.w2m.aplicacion.dto.JwtResponse;
import com.w2m.application.service.AdministratorService;
import com.w2m.infrastructure.annotation.LogExecutionTime;
import com.w2m.infrastructure.securization.JwtToken;
import com.w2m.presentacion.api.AutenticacionApi;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin
public class AutenticacionController implements AutenticacionApi {
	private final AuthenticationManager authenticationManager;
	private final JwtToken jwtToken;
	private final AdministratorService administratorService;

	@LogExecutionTime
	@Override
	public ResponseEntity<JwtResponse> autenticacionLoginPost(@Valid JwtRequest jwtRequest) {
		authenticate(jwtRequest.getIdentificator(), jwtRequest.getPassword(), Collections.emptyList());
		String token = generateToken(jwtRequest.getIdentificator());
		return ResponseEntity.ok(JwtResponse.builder().jwttoken(token).build());
	}

	private String generateToken(String identificador) {
		log.info("generate token");
		return jwtToken.generateToken(administratorService.findById(identificador));
	}

	private void authenticate(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, authorities));
	}
}
