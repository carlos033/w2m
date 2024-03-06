package com.w2m.domain.serviceimpl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;
import com.w2m.application.adapter.SuperHeroAdapter;
import com.w2m.application.service.SuperHeroeService;
import com.w2m.domain.exception.NotContentW2M;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@Slf4j
@AllArgsConstructor
@Service
public class SuperHeroServiceImpl implements SuperHeroeService{
	private static final String MESSAGE = "We call the adapter";
	private static final String MESSAGE2 = "No superheroes were found with the provided fragment.";
	private static final String MESSAGE3 = "Rate not found in the database";
	private final SuperHeroAdapter adapter;

	@Override
	public SuperHeroDTO createSuperHero(SuperHeroDTOEntrance dto) {
		return adapter.save(dto);
	}

	@Override
	public SuperHeroDTO findById(long id) {
		log.info(MESSAGE);
		return adapter.findById(id)
				.orElseThrow(() -> new NotContentW2M(HttpStatus.NO_CONTENT, MESSAGE3));
	}

	@Override
	public List<SuperHeroDTO> searchByFragment(String fragment) {
		log.info(MESSAGE);
		List<SuperHeroDTO> superHeroList = adapter.findByNameContaining(fragment);
		if (superHeroList.isEmpty()) {
			throw new NotContentW2M(HttpStatus.NO_CONTENT, MESSAGE2);
		}
		return List.copyOf(superHeroList);
	}

	@Override
	public List<SuperHeroDTO> findALL() {
		log.info(MESSAGE);
		return adapter.findAll();
	}

	@Override
	public void deleteHero(long id) {
		log.info("We search by id to see if said superhero exists");
		findById(id);
		log.info(MESSAGE);
		adapter.deleteById(id);
	}

	@Override
	public SuperHeroDTO modifySuperHero(SuperHeroDTO dto) {
		log.info(MESSAGE);
		return adapter.modify(dto);
	}

}
