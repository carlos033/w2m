package com.w2m.infraesctructura.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.w2m.infrastructure.entity.Skill;
import com.w2m.infrastructure.entity.SuperHero;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class SkillTest {

	@Test
	void testNoArgsConstructor() {
		// Given
		Skill skill = Skill.builder().build();
		skill.setName("a");
		skill.setSkillId(1L);
		skill.setSuperHero(new SuperHero());
		// Then
		assertNotNull(skill);
	}

	@Test
	void testGetSetSkillId() {
		// Given
		long expectedSkillId = 2L;

		// When
		Skill skill = Skill.builder().skillId(expectedSkillId).build();

		// Then
		assertEquals(expectedSkillId, skill.getSkillId());
	}

	@Test
	void testGetSetName() {
		// Given
		String expectedName = "super fuerza";

		// When
		Skill skill = Skill.builder().name(expectedName).build();

		// Then
		assertEquals(expectedName, skill.getName());
	}

	@Test
	void testGetSetSuperHero() {
		// Given
		SuperHero superHero = SuperHero.builder().build();

		// When
		Skill skill = Skill.builder().superHero(superHero).build();

		// Then
		assertEquals(superHero, skill.getSuperHero());
	}

	@Test
	void testAllArgsConstructor() {
		// Given
		long expectedSkillId = 2L;
		String expectedName = "super fuerza";
		SuperHero superHero = SuperHero.builder().build();

		// When
		Skill skill = Skill.builder().skillId(expectedSkillId).name(expectedName).superHero(superHero).build();

		// Then
		assertEquals(expectedSkillId, skill.getSkillId());
		assertEquals(expectedName, skill.getName());
		assertEquals(superHero, skill.getSuperHero());
	}
}
