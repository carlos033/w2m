package com.w2m.dominio.servicioImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.w2m.application.adapter.AdministratorAdapter;
import com.w2m.domain.serviceimpl.JwtUserServiceImpl;
import com.w2m.infrastructure.entity.Administrator;
/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class ServiciosJwtUsuariosImplTest{

	@Mock
	AdministratorAdapter adapter;


	@InjectMocks
	JwtUserServiceImpl servicioJwtUsuarios;

	@Test
	void testLoadUserByUsername_UserFound() {
		// Given
		String username = "testUser";
		String password = "testPassword";
		Administrator administrator =
				Administrator.builder().username("username").name("user1").password("password").build();
		administrator.setUsername(username);
		administrator.setPassword(password);

		when(adapter.findById(username)).thenReturn(Optional.of(administrator));

		JwtUserServiceImpl jwtUserService = new JwtUserServiceImpl(adapter);

		// When
		UserDetails userDetails = jwtUserService.loadUserByUsername(username);

		// Then
		assertNotNull(userDetails);
		assertEquals(username, userDetails.getUsername());
		assertEquals(password, userDetails.getPassword());
		assertTrue(userDetails.getAuthorities().isEmpty());
	}

	@Test
	void testLoadUserByUsername_UserNotFound() {
		// Given
		String username = "nonExistingUser";

		when(adapter.findById(username)).thenReturn(Optional.empty());

		JwtUserServiceImpl jwtUserService = new JwtUserServiceImpl(adapter);

		// When & Then
		assertThrows(UsernameNotFoundException.class,
				() -> jwtUserService.loadUserByUsername(username));
	}
}
