package com.w2m.application.service;

import java.util.List;
import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
public interface SuperHeroeService{
	SuperHeroDTO createSuperHero(SuperHeroDTOEntrance dto);

	SuperHeroDTO modifySuperHero(SuperHeroDTO dto);

	SuperHeroDTO findById(long id);

	List<SuperHeroDTO> searchByFragment(String fragment);

	List<SuperHeroDTO> findALL();

	void deleteHero(long id);
}
