package com.w2m.aplicacion.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import com.w2m.aplicacion.dto.SkillDTO;
import com.w2m.aplicacion.dto.SkillDTOEntrance;
import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;
import com.w2m.application.mapper.SuperHeroMapper;
import com.w2m.infrastructure.entity.Skill;
import com.w2m.infrastructure.entity.SuperHero;

class SuperHeroMapperTest{

	private final SuperHeroMapper mapper = Mappers.getMapper(SuperHeroMapper.class);

	@Test
	void testConvertToDTO() {
		SuperHero entity =
				SuperHero.builder().idSuperhero(1L).name("Batman").civilIdentity("Bruce Wayne").build();

		SuperHeroDTO dto = mapper.convertToDTO(entity);

		assertEquals(1L, dto.getIdSuperhero());
		assertEquals("Batman", dto.getName());
		assertEquals("Bruce Wayne", dto.getCivilIdentity());
		assertNull(dto.getSkillList());
	}

	@Test
	void testConvertToEntity() {
		SuperHeroDTO dto =
				SuperHeroDTO.builder().idSuperhero(1L).name("Superman").civilIdentity("Clark Kent").build();

		SuperHero entity = mapper.convertToEntity(dto);

		assertEquals(1L, entity.getIdSuperhero());
		assertEquals("Superman", entity.getName());
		assertEquals("Clark Kent", entity.getCivilIdentity());
		assertNull(entity.getSkillList());
	}

	@Test
	void testConvertToEntraceEntity() {
		SuperHeroDTOEntrance dtoEntrance = SuperHeroDTOEntrance.builder().build();
		dtoEntrance.setName("Wonder Woman");
		dtoEntrance.setCivilIdentity("Diana Prince");

		SuperHero entity = mapper.convertToEntraceEntity(dtoEntrance);

		assertNull(entity.getIdSuperhero());
		assertEquals("Wonder Woman", entity.getName());
		assertEquals("Diana Prince", entity.getCivilIdentity());
		assertNull(entity.getSkillList());
	}

	@Test
	void testConvertToDTOSkill() {
		Skill skill = Skill.builder().skillId(1L).name("Super Strength").build();

		SkillDTO dto = mapper.convertToDTOSkill(skill);

		assertEquals(1L, dto.getSkillId());
		assertEquals("Super Strength", dto.getName());
	}

	@Test
	void testConvertToEntitySkill() {
		SkillDTO dto = SkillDTO.builder().skillId(1L).name("Flight").build();

		Skill skill = mapper.convertToEntitySkill(dto);

		assertEquals(1L, skill.getSkillId());
		assertEquals("Flight", skill.getName());
	}

	@Test
	void testConvertToEntityEntraceSkill() {
		SkillDTOEntrance dtoEntrace = SkillDTOEntrance.builder().build();
		dtoEntrace.setName("Telepathy");

		Skill skill = mapper.convertToEntityEntraceSkill(dtoEntrace);

		assertNotNull(skill);
		assertEquals("Telepathy", skill.getName());
	}

	@Test
	void testConvertToDTO2() {
		List<Skill> skills = new ArrayList<>();
		Skill skill = Skill.builder().name("Flying").build();
		skills.add(skill);

		SuperHero superHero = SuperHero.builder().idSuperhero(1L).name("Superman")
				.civilIdentity("Clark Kent").skillList(skills).build();

		SuperHeroDTO dto = mapper.convertToDTO(superHero);

		assertEquals(1L, dto.getIdSuperhero());
		assertEquals("Superman", dto.getName());
		assertEquals("Clark Kent", dto.getCivilIdentity());
		assertEquals(1, dto.getSkillList().size());
		assertEquals("Flying", dto.getSkillList().get(0).getName());
	}

	@Test
	void testConvertToEntraceEntity2() {
		List<SkillDTOEntrance> SkillDTOEntrances = new ArrayList<>();
		SkillDTOEntrance skillDTOEntrance = SkillDTOEntrance.builder().build();
		skillDTOEntrance.setName("Strength");
		SkillDTOEntrances.add(skillDTOEntrance);

		SuperHeroDTOEntrance dtoEntrance = SuperHeroDTOEntrance.builder().build();
		dtoEntrance.setName("Wonder Woman");
		dtoEntrance.setCivilIdentity("Diana Prince");
		dtoEntrance.setSkillList(SkillDTOEntrances);

		SuperHero superHero = mapper.convertToEntraceEntity(dtoEntrance);

		assertNull(superHero.getIdSuperhero());
		assertEquals("Wonder Woman", superHero.getName());
		assertEquals("Diana Prince", superHero.getCivilIdentity());
		assertEquals(1, superHero.getSkillList().size());
		assertEquals("Strength", superHero.getSkillList().get(0).getName());
	}

	@Test
	void testConvertToEntity2() {
		List<SkillDTO> skillDTOs = new ArrayList<>();
		SkillDTO skillDTO = SkillDTO.builder().name("Flying").build();
		skillDTOs.add(skillDTO);

		SuperHeroDTO dto = SuperHeroDTO.builder().idSuperhero(1L).name("Superman")
				.civilIdentity("Clark Kent").skillList(skillDTOs).build();

		SuperHero superHero = mapper.convertToEntity(dto);

		assertEquals(1L, superHero.getIdSuperhero());
		assertEquals("Superman", superHero.getName());
		assertEquals("Clark Kent", superHero.getCivilIdentity());
		assertEquals(1, superHero.getSkillList().size());
		assertEquals("Flying", superHero.getSkillList().get(0).getName());
	}
}
