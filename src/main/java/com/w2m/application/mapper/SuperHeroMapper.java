package com.w2m.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.w2m.aplicacion.dto.SkillDTO;
import com.w2m.aplicacion.dto.SkillDTOEntrance;
import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;
import com.w2m.infrastructure.entity.Skill;
import com.w2m.infrastructure.entity.SuperHero;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@Mapper(componentModel = "spring")
public interface SuperHeroMapper{
	SuperHeroDTO convertToDTO(SuperHero entity);

	SuperHero convertToEntity(SuperHeroDTO dTOS);

	@Mapping(target = "idSuperhero", ignore = true)
	SuperHero convertToEntraceEntity(SuperHeroDTOEntrance dTOS);

	SkillDTO convertToDTOSkill(Skill entity);

	@Mapping(target = "superHero", ignore = true)
	Skill convertToEntitySkill(SkillDTO dto);

	@Mapping(target = "skillId", ignore = true)
	@Mapping(target = "superHero", ignore = true)
	Skill convertToEntityEntraceSkill(SkillDTOEntrance dTOS);

}
