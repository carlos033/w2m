package com.w2m.aplicacion.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SkillDTOEntranceTest{
	@InjectMocks
	private SkillDTOEntrance skillDTO;

	@Test
	void testEqualsAndHashCode() {
		SkillDTOEntrance firstSkill = new SkillDTOEntrance("Coding");
		SkillDTOEntrance secondSkill = new SkillDTOEntrance("Coding");

		assertEquals(firstSkill, secondSkill);
		assertEquals(firstSkill.hashCode(), secondSkill.hashCode());
	}

}
