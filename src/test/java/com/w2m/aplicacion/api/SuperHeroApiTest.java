package com.w2m.aplicacion.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;
import com.w2m.presentacion.api.SuperHeroApi;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class SuperHeroApiTest{

	private final SuperHeroApi superHeroeApi = new SuperHeroApi(){
		@Override
		public Optional<NativeWebRequest> getRequest() {
			return Optional.empty();
		}
	};

	@Test
	void getRequestReturnsEmptyOptional() {
		Optional<NativeWebRequest> request = superHeroeApi.getRequest();
		assertTrue(request.isEmpty());
	}

	@Test
	void borradoDeUnSuperhroeReturnsNotImplemented() {

		ResponseEntity<Void> response = superHeroeApi.delete(1L);

		assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
	}

	@Test
	void buscarTodosLosSuperheroesReturnsNotImplemented() {

		ResponseEntity<List<SuperHeroDTO>> response = superHeroeApi.findAll();


		assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
	}

	@Test
	void buscarTodosLosSuperheroesXFragmentoReturnsNotImplemented() {

		String fragmentoNombre = "super";


		ResponseEntity<List<SuperHeroDTO>> response =
				superHeroeApi.searchAllByFragment(fragmentoNombre);


		assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
	}

	@Test
	void crearSuperheroeReturnsNotImplemented() {

		SuperHeroDTOEntrance SuperHeroDTOEntrada = SuperHeroDTOEntrance.builder().build();


		ResponseEntity<SuperHeroDTO> response = superHeroeApi.create(SuperHeroDTOEntrada);


		assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
	}

	@Test
	void modificarUnSuperhroeReturnsNotImplemented() {

		SuperHeroDTO superHeroDTO = SuperHeroDTO.builder().civilIdentity("Tachala").idSuperhero(1L)
				.name("Pantera negra").skillList(new ArrayList<>()).build();
		ResponseEntity<SuperHeroDTO> response = superHeroeApi.modify(superHeroDTO);


		assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
	}

	@Test
	void obtenerSuperheroeXidReturnsNotImplemented() {


		ResponseEntity<SuperHeroDTO> response = superHeroeApi.getById(1L);


		assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
	}
}
