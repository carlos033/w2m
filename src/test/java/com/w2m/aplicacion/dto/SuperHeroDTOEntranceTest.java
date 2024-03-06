package com.w2m.aplicacion.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SuperHeroDTOEntranceTest{

	private SuperHeroDTOEntrance superHeroDTO;

	@BeforeEach
	void setUp() {
		superHeroDTO =
				SuperHeroDTOEntrance.builder().name("Superman").civilIdentity("Clark Kent").build();
	}

	@Test
	void testToString() {
		String expected = """
				class SuperHeroDTOEntrance {
				    name: Superman
				    civilIdentity: Clark Kent
				    skillList: null
				}\
				""";
		String actual = superHeroDTO.toString();


		assertEquals(expected, actual);
	}

	@Test
	void testEqualsAndHashCode() {

		SuperHeroDTOEntrance superHeroDTO2 =
				SuperHeroDTOEntrance.builder().name("Superman").civilIdentity("Clark Kent").build();

		SuperHeroDTOEntrance superHeroDTO3 =
				SuperHeroDTOEntrance.builder().build().name("Batman").civilIdentity("Bruce Wayne");

		assertEquals(superHeroDTO, superHeroDTO2);
		assertNotEquals(superHeroDTO, superHeroDTO3);
		assertEquals(superHeroDTO.hashCode(), superHeroDTO2.hashCode());
	}

	@Test
	void testGetterAndSetter() {

		String expectedName = "Superman";
		String expectedCivilIdentity = "Clark Kent";
		List<SkillDTOEntrance> expectedSkillList = new ArrayList<>();

		superHeroDTO.setSkillList(expectedSkillList);

		assertEquals(expectedName, superHeroDTO.getName());
		assertEquals(expectedCivilIdentity, superHeroDTO.getCivilIdentity());
		assertEquals(expectedSkillList, superHeroDTO.getSkillList());
	}

	@Test
	void testAddSkillListItem() {

		SkillDTOEntrance skillDTO = SkillDTOEntrance.builder().build();
		List<SkillDTOEntrance> expectedSkillList = new ArrayList<>();
		expectedSkillList.add(skillDTO);

		superHeroDTO.addSkillListItem(skillDTO);

		assertEquals(expectedSkillList, superHeroDTO.getSkillList());
	}

	@Test
	void testAllArgsConstructor() {

		String expectedName = "Superman";
		String expectedCivilIdentity = "Clark Kent";
		List<SkillDTOEntrance> expectedSkillList = new ArrayList<>();

		SuperHeroDTOEntrance dto =
				new SuperHeroDTOEntrance(expectedName, expectedCivilIdentity, expectedSkillList);

		assertEquals(expectedName, dto.getName());
		assertEquals(expectedCivilIdentity, dto.getCivilIdentity());
		assertEquals(expectedSkillList, dto.getSkillList());
	}

	@Test
	void testBuilder() {

		String expectedName = "Batman";
		String expectedCivilIdentity = "Bruce Wayne";
		List<SkillDTOEntrance> expectedSkillList = new ArrayList<>();

		SuperHeroDTOEntrance dto = SuperHeroDTOEntrance.builder().name(expectedName)
				.civilIdentity(expectedCivilIdentity).skillList(expectedSkillList).build();

		assertEquals(expectedName, dto.getName());
		assertEquals(expectedCivilIdentity, dto.getCivilIdentity());
		assertEquals(expectedSkillList, dto.getSkillList());
	}

	@Test
	void testSetName() {

		String expectedName = "Spiderman";

		superHeroDTO.setName(expectedName);

		assertEquals(expectedName, superHeroDTO.getName());
	}

	@Test
	void testSetCivilIdentity() {
		String expectedCivilIdentity = "Peter Parker";

		superHeroDTO.setCivilIdentity(expectedCivilIdentity);

		assertEquals(expectedCivilIdentity, superHeroDTO.getCivilIdentity());
	}

	@Test
	void testSkillList() {
		List<SkillDTOEntrance> expectedSkillList = new ArrayList<>();
		SkillDTOEntrance skill1 = SkillDTOEntrance.builder().build();
		SkillDTOEntrance skill2 = SkillDTOEntrance.builder().build();
		expectedSkillList.add(skill1);
		expectedSkillList.add(skill2);

		superHeroDTO.skillList(expectedSkillList);

		assertEquals(expectedSkillList, superHeroDTO.getSkillList());
	}
}
