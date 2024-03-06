package com.w2m.presentacion.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;
import com.w2m.application.service.SuperHeroeService;
import com.w2m.presentation.SuperHeroeController;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class SuperHeroeControllerTest{

	@InjectMocks
	private SuperHeroeController superHeroeController;

	@Mock
	private SuperHeroeService servicioHeroes;

	@Test
	void testFindAllSuperheroes() {
		List<SuperHeroDTO> superHeroes = new ArrayList<>();
		superHeroes.add(SuperHeroDTO.builder().idSuperhero(1L).name("Batman")
				.civilIdentity("Bruce Wayne").skillList(new ArrayList<>()).build());
		when(servicioHeroes.findALL()).thenReturn(superHeroes);

		ResponseEntity<List<SuperHeroDTO>> response = superHeroeController.findAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(superHeroes, response.getBody());
	}

	@Test
	void testSearchAllSuperheroesByFragment() {
		String fragmentName = "fragment";
		List<SuperHeroDTO> superHeroes = new ArrayList<>();
		superHeroes.add(SuperHeroDTO.builder().idSuperhero(1L).name("Batman")
				.civilIdentity("Bruce Wayne").skillList(new ArrayList<>()).build());
		when(servicioHeroes.searchByFragment(fragmentName)).thenReturn(superHeroes);

		ResponseEntity<List<SuperHeroDTO>> response =
				superHeroeController.searchAllByFragment(fragmentName);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(superHeroes, response.getBody());
	}

	@Test
	void testCreateSuperhero() {
		SuperHeroDTOEntrance superHeroeDTOEntrance = SuperHeroDTOEntrance.builder().build();
		superHeroeDTOEntrance.setName("Batman");
		superHeroeDTOEntrance.setCivilIdentity("Bruce Wayne");
		SuperHeroDTO superHeroDTO = SuperHeroDTO.builder().idSuperhero(1L).name("Batman")
				.civilIdentity("Bruce Wayne").skillList(new ArrayList<>()).build();
		when(servicioHeroes.createSuperHero(superHeroeDTOEntrance)).thenReturn(superHeroDTO);

		ResponseEntity<SuperHeroDTO> response = superHeroeController.create(superHeroeDTOEntrance);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(superHeroDTO, response.getBody());
	}

	@Test
	void testDeleteASuperhero() {
		Long id = 1L;
		ResponseEntity<Void> response = superHeroeController.delete(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testGetSuperheroById() {
		Long id = 1L;
		SuperHeroDTO superHeroDTO = SuperHeroDTO.builder().idSuperhero(1L).name("Batman")
				.civilIdentity("Bruce Wayne").skillList(new ArrayList<>()).build();
		when(servicioHeroes.findById(id)).thenReturn(superHeroDTO);

		ResponseEntity<SuperHeroDTO> response = superHeroeController.getById(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(superHeroDTO, response.getBody());
	}

	@Test
	void testModifyASuperhroe() {
		SuperHeroDTO superHeroDTO = SuperHeroDTO.builder().idSuperhero(1L).name("Batman")
				.civilIdentity("Bruce Wayne").skillList(new ArrayList<>()).build();
		when(servicioHeroes.modifySuperHero(superHeroDTO)).thenReturn(superHeroDTO);

		ResponseEntity<SuperHeroDTO> response = superHeroeController.modify(superHeroDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(superHeroDTO, response.getBody());
	}
}


