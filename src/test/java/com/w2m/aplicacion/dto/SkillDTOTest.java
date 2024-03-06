package com.w2m.aplicacion.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SkillDTOTest{
	@InjectMocks
	private SkillDTO skillDTO;

	@Test
	void testEqualsAndHashCode() {
		SkillDTO firstSkill = new SkillDTO(1L, "Coding");
		SkillDTO secondSkill = new SkillDTO(1L, "Coding");

		assertEquals(firstSkill, secondSkill);
		assertEquals(firstSkill.hashCode(), secondSkill.hashCode());
	}


	@Test
	void testToString() {
		SkillDTO skill = SkillDTO.builder().build();
		skill.setSkillId(123L);
		skill.setName("Super Strength");

		String expectedOutput = """
				class SkillDTO {
				    skillId: 123
				    name: Super Strength
				}\
				""";
		assertEquals(expectedOutput, skill.toString());
	}
}
