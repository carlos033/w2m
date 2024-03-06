package com.w2m.infraesctructura.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.w2m.infrastructure.entity.Skill;
import com.w2m.infrastructure.entity.SuperHero;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class SuperHeroTest {
	@Test
	void testAllArgsConstructor() {
		// Given
		String expectedName = "Batman";
		String expectedCivilIdentity = "Bruce Wayne";
		List<Skill> expectedSkillList = new ArrayList<>();

		// When
		SuperHero superHero = SuperHero.builder().name(expectedName).civilIdentity(expectedCivilIdentity)
				.skillList(expectedSkillList).build();

		// Then
		assertEquals(expectedName, superHero.getName(), "Name does not match expected value");
		assertEquals(expectedCivilIdentity, superHero.getCivilIdentity(),
				"Civil identity does not match expected value");
		assertEquals(expectedSkillList, superHero.getSkillList(), "Skill list does not match expected value");
	}

	@Test
	void testGetSet() {
		// Given
		String expectedName = "Batman";

		// When
		SuperHero superHero = SuperHero.builder().build();
		superHero.setCivilIdentity("a");
		superHero.setIdSuperhero(1L);
		superHero.setName(expectedName);
		superHero.setSkillList(new ArrayList<>());
		// Then
		assertEquals(expectedName, superHero.getName(), "Name does not match expected value");
	}

	@Test
	void testGetSetName() {
		// Given
		String expectedName = "Batman";

		// When
		SuperHero superHero = SuperHero.builder().name(expectedName).build();

		// Then
		assertEquals(expectedName, superHero.getName(), "Name does not match expected value");
	}

	@Test
	void testGetSetCivilIdentity() {
		// Given
		String expectedCivilIdentity = "Bruce Wayne";

		// When
		SuperHero superHero = SuperHero.builder().civilIdentity(expectedCivilIdentity).build();

		// Then
		assertEquals(expectedCivilIdentity, superHero.getCivilIdentity(),
				"Civil identity does not match expected value");
	}

	@Test
	void testGetSetSkillList() {
		// Given
		List<Skill> expectedSkillList = new ArrayList<>();

		// When
		SuperHero superHero = SuperHero.builder().skillList(expectedSkillList).build();

		// Then
		assertEquals(expectedSkillList, superHero.getSkillList(), "Skill list does not match expected value");
	}
}
