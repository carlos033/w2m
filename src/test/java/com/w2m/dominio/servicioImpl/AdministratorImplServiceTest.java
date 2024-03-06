package com.w2m.dominio.servicioImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.w2m.application.adapter.AdministratorAdapter;
import com.w2m.domain.exception.NotContentW2M;
import com.w2m.domain.serviceimpl.AdministratorImplService;
import com.w2m.infrastructure.entity.Administrator;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class AdministratorImplServiceTest{

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@Mock
	private AdministratorAdapter adaptador;

	@InjectMocks
	private AdministratorImplService administratorService;

	@Test
	void findById_UserExists_ReturnsAdministrator() {
		// Given
		String username = "admin";
		Administrator expectedAdministrator =
				Administrator.builder().username("username").name("user1").password("password").build();
		when(adaptador.findById(username)).thenReturn(Optional.of(expectedAdministrator));

		// When
		Administrator actualAdministrator = administratorService.findById(username);

		// Then
		assertEquals(expectedAdministrator, actualAdministrator);
		verify(adaptador, times(1)).findById(username);
	}

	@Test
	void findById_UserDoesNotExist_ThrowsException() {
		// Given
		String username = "admin";
		when(adaptador.findById(username)).thenReturn(Optional.empty());

		// When, Then
		assertThrows(NotContentW2M.class, () -> administratorService.findById(username));
		verify(adaptador, times(1)).findById(username);
	}
}
