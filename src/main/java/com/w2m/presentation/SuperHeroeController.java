package com.w2m.presentation;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;
import com.w2m.application.service.SuperHeroeService;
import com.w2m.infrastructure.annotation.LogExecutionTime;
import com.w2m.presentacion.api.SuperHeroApi;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@CrossOrigin
@AllArgsConstructor
@RestController
public class SuperHeroeController implements SuperHeroApi{
	private final SuperHeroeService heroService;

	@LogExecutionTime
	@Override
	public ResponseEntity<List<SuperHeroDTO>> findAll() {
		return ResponseEntity.ok(heroService.findALL());
	}

	@LogExecutionTime
	@Override
	public ResponseEntity<List<SuperHeroDTO>> searchAllByFragment(String fragmentName) {

		return ResponseEntity.ok(heroService.searchByFragment(fragmentName));
	}

	@LogExecutionTime
	@Override
	public ResponseEntity<SuperHeroDTO> create(@Valid SuperHeroDTOEntrance superHeroeDTOEntrance) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(heroService.createSuperHero(superHeroeDTOEntrance));
	}

	@LogExecutionTime
	@Override
	public ResponseEntity<Void> delete(Long id) {
		heroService.deleteHero(id);
		return ResponseEntity.ok().build();
	}

	@LogExecutionTime
	@Override
	public ResponseEntity<SuperHeroDTO> getById(Long id) {

		return ResponseEntity.ok(heroService.findById(id));
	}

	@LogExecutionTime
	@Override
	public ResponseEntity<SuperHeroDTO> modify(@Valid SuperHeroDTO superHeroDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(heroService.modifySuperHero(superHeroDTO));
	}
}
