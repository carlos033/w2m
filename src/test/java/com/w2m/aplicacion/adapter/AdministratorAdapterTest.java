package com.w2m.aplicacion.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.w2m.application.adapter.AdministratorAdapter;
import com.w2m.domain.repository.AdministratorRepository;
import com.w2m.infrastructure.entity.Administrator;

@ExtendWith(MockitoExtension.class)
class AdministratorAdapterTest {

	@Mock
	private AdministratorRepository userRepository;

	@InjectMocks
	private AdministratorAdapter administratorAdapter;

	@Test
	void testFindById() {
		String userId = "123";
		Administrator administrator = Administrator.builder().build();
		when(userRepository.findById(userId)).thenReturn(Optional.of(administrator));

		Optional<Administrator> result = administratorAdapter.findById(userId);

		assertEquals(Optional.of(administrator), result);
		verify(userRepository, times(1)).findById(userId);
	}
}
