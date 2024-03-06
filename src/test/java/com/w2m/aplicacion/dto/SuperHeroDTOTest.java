package com.w2m.aplicacion.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
import org.junit.jupiter.api.Test;

class SuperHeroDTOTest{

	private SuperHeroDTO superHeroDTO;

	@BeforeEach
	void setUp() {
		superHeroDTO = SuperHeroDTO.builder().build();
	}

	@Test
	void testIdSuperhero() {
		Long expectedId = 1L;
		superHeroDTO.setIdSuperhero(expectedId);
		assertEquals(expectedId, superHeroDTO.getIdSuperhero());
	}

	@Test
	void testName() {
		String expectedName = "Superman";
		superHeroDTO.setName(expectedName);
		assertEquals(expectedName, superHeroDTO.getName());
	}

	@Test
	void testCivilIdentity() {
		String expectedCivilIdentity = "Clark Kent";
		superHeroDTO.setCivilIdentity(expectedCivilIdentity);
		assertEquals(expectedCivilIdentity, superHeroDTO.getCivilIdentity());
	}

	@Test
	void testSkillList() {
		List<SkillDTO> expectedSkillList = new ArrayList<>();
		SkillDTO skill1 = SkillDTO.builder().build();
		SkillDTO skill2 = SkillDTO.builder().build();
		expectedSkillList.add(skill1);
		expectedSkillList.add(skill2);
		superHeroDTO.setSkillList(expectedSkillList);
		assertEquals(expectedSkillList, superHeroDTO.getSkillList());
	}

	@Test
	void testEqualsAndHashCode() {
		SuperHeroDTO superHeroDTO1 =
				SuperHeroDTO.builder().build().idSuperhero(1L).name("Superman").civilIdentity("Clark Kent");
		SuperHeroDTO superHeroDTO2 =
				SuperHeroDTO.builder().build().idSuperhero(1L).name("Superman").civilIdentity("Clark Kent");
		assertTrue(superHeroDTO1.equals(superHeroDTO2) && superHeroDTO2.equals(superHeroDTO1));
		assertEquals(superHeroDTO1.hashCode(), superHeroDTO2.hashCode());
	}

	@Test
	void testToString() {
		String expectedString = """
				class SuperHeroDTO {
				    idSuperhero: 1
				    name: Superman
				    civilIdentity: Clark Kent
				    skillList: null
				}\
				""";
		SuperHeroDTO superHeroDTO =
				SuperHeroDTO.builder().build().idSuperhero(1L).name("Superman").civilIdentity("Clark Kent");
		assertEquals(expectedString, superHeroDTO.toString());
	}



	@Test
	void testAddSkillListItem() {
		SkillDTO skill1 = SkillDTO.builder().build();
		superHeroDTO.addSkillListItem(skill1);
		assertEquals(1, superHeroDTO.getSkillList().size());
		assertEquals(skill1, superHeroDTO.getSkillList().get(0));
	}

	@Test
	void testAllArgsConstructor() {
		Long expectedId = 1L;
		String expectedName = "Superman";
		String expectedCivilIdentity = "Clark Kent";
		List<SkillDTO> expectedSkillList = new ArrayList<>();
		SkillDTO skill1 = SkillDTO.builder().build();
		SkillDTO skill2 = SkillDTO.builder().build();
		expectedSkillList.add(skill1);
		expectedSkillList.add(skill2);

		superHeroDTO =
				new SuperHeroDTO(expectedId, expectedName, expectedCivilIdentity, expectedSkillList);

		assertEquals(expectedId, superHeroDTO.getIdSuperhero());
		assertEquals(expectedName, superHeroDTO.getName());
		assertEquals(expectedCivilIdentity, superHeroDTO.getCivilIdentity());
		assertEquals(expectedSkillList, superHeroDTO.getSkillList());
	}

	@Test
	void testSkillListBuilder() {
		List<SkillDTO> expectedSkillList = new ArrayList<>();
		SkillDTO skill1 = SkillDTO.builder().build();
		SkillDTO skill2 = SkillDTO.builder().build();
		expectedSkillList.add(skill1);
		expectedSkillList.add(skill2);

		superHeroDTO = SuperHeroDTO.builder().skillList(expectedSkillList).build();

		assertEquals(expectedSkillList, superHeroDTO.getSkillList());
	}

}
