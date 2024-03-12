package com.w2m.dominio.servicioImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;
import com.w2m.application.adapter.SuperHeroAdapter;
import com.w2m.domain.exception.NotContentW2M;
import com.w2m.domain.serviceimpl.SuperHeroServiceImpl;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class ServicioHeroImplTest{

	@Mock
	SuperHeroAdapter superHeroAdapter;

	@InjectMocks
	SuperHeroServiceImpl superHeroService;

	@Test
	void testCreateSuperHero() {
		// Given
		SuperHeroDTOEntrance dto = SuperHeroDTOEntrance.builder().build();
		SuperHeroDTO expectedDTO = SuperHeroDTO.builder().build();

		when(superHeroAdapter.save(dto)).thenReturn(expectedDTO);

		// When
		SuperHeroDTO result = superHeroService.createSuperHero(dto);

		// Then
		assertNotNull(result);
		assertEquals(expectedDTO, result);
	}

	@Test
	void testFindById_ExistingSuperHero() {
		// Given
		long id = 1L;
		SuperHeroDTO expectedDTO = SuperHeroDTO.builder().build();
		expectedDTO.setIdSuperhero(id);
		when(superHeroAdapter.findById(id)).thenReturn(Optional.of(expectedDTO));

		// When
		SuperHeroDTO result = superHeroService.findById(id);

		// Then
		assertNotNull(result);
		assertEquals(expectedDTO, result);
	}

	@Test
	void testFindById_NonExistingSuperHero() {
		// Given
		long id = 1L;

		when(superHeroAdapter.findById(id)).thenReturn(Optional.empty());

		// When & Then
		assertThrows(NotContentW2M.class, () -> superHeroService.findById(id));
	}

	@Test
	void testSearchByFragment_FoundSuperHeroes() {
		// Given
		SuperHeroDTO superHeroDTO = SuperHeroDTO.builder().idSuperhero(1L).name("Superman")
				.civilIdentity("Clark").skillList(new ArrayList<>()).build();
		String fragment = "man";
		List<SuperHeroDTO> expectedList = new ArrayList<>();
		expectedList.add(superHeroDTO);
		when(superHeroAdapter.findByNameContaining(fragment)).thenReturn(expectedList);

		// When
		List<SuperHeroDTO> result = superHeroService.searchByFragment(fragment);

		// Then
		assertNotNull(result);
		assertEquals(expectedList, result);
	}

	@Test
	void testSearchByFragment_NoSuperHeroesFound() {
		// Given
		String fragment = "xyz";

		when(superHeroAdapter.findByNameContaining(fragment)).thenReturn(new ArrayList<>());

		// When & Then
		assertThrows(NotContentW2M.class, () -> superHeroService.searchByFragment(fragment));
	}

	@Test
	void testFindALL() {
		// Given
		List<SuperHeroDTO> expectedList = new ArrayList<>();

		when(superHeroAdapter.findAll()).thenReturn(expectedList);

		// When
		List<SuperHeroDTO> result = superHeroService.findALL();

		// Then
		assertNotNull(result);
		assertEquals(expectedList, result);
	}

	@Test
	void testDeleteHero_NonExistingSuperHero() {
		// Given
		long id = 1L;
		when(superHeroAdapter.findById(id)).thenReturn(Optional.empty());

		// When & Then
		assertThrows(NotContentW2M.class, () -> superHeroService.deleteHero(id));
		verify(superHeroAdapter, never()).deleteById(id);
	}
}
