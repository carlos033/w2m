package com.w2m.dominio.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.w2m.domain.repository.AdministratorRepository;
import com.w2m.domain.repository.SkillRepository;
import com.w2m.domain.repository.SuperHeroRepository;
import com.w2m.infrastructure.entity.Administrator;
import com.w2m.infrastructure.entity.Skill;
import com.w2m.infrastructure.entity.SuperHero;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */

@DataJpaTest
class RepositorioJpaTest{

	@Autowired
	private AdministratorRepository usuarioRepository;

	@Autowired
	private SkillRepository habilidadRepository;

	@Autowired
	private SuperHeroRepository superHeroeRepository;

	@Test
	void testGuardarYBuscarAdministrador() {
		// Given
		Administrator user =
				Administrator.builder().username("user").name("user1").password("password").build();

		// When
		usuarioRepository.save(user);

		// Then
		Administrator administrator = usuarioRepository.findById("user").orElse(null);
		assertNotNull(administrator);
		assertEquals("password", administrator.getPassword());
	}

	@Test
	void testGuardarYBuscarSuperHeroe() {
		// Given
		SuperHero superHero = SuperHero.builder().idSuperhero(1L).name("Superman").build();
		superHeroeRepository.save(superHero);

		Skill skill = Skill.builder().skillId(1L).name("Flight").superHero(superHero).build();
		habilidadRepository.save(skill);

		superHero.setSkillList(List.of(skill));
		superHeroeRepository.save(superHero);

		// When
		SuperHero encontrado = superHeroeRepository.findById(1L).orElse(null);

		// Then
		assertNotNull(encontrado);
		assertEquals("Superman", encontrado.getName());
	}

	@Test
	void testFindByNombreContaining() {
		// When
		List<SuperHero> found = superHeroeRepository.findByNameContaining("Spider");

		// Then
		assertThat(found).hasSize(1);
	}
}
