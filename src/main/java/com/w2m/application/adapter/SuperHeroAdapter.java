package com.w2m.application.adapter;

import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;
import com.w2m.application.mapper.SuperHeroMapper;
import com.w2m.domain.exception.NotContentW2M;
import com.w2m.domain.repository.SkillRepository;
import com.w2m.domain.repository.SuperHeroRepository;
import com.w2m.infrastructure.entity.Skill;
import com.w2m.infrastructure.entity.SuperHero;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Component
@Slf4j
public class SuperHeroAdapter {
	private static final String MESSAGE3 = "Rate not found in the database";
	private SuperHeroRepository superHeroRepository;
	private SkillRepository skillRepository;
	private SuperHeroMapper mapper;

	public SuperHeroDTO save(SuperHeroDTOEntrance dto) {
		SuperHero entity = mapper.convertToEntraceEntity(dto);
		log.info("We save every ability of the superhero in the DB");
		entity.getSkillList().forEach(skill -> skill.setSuperHero(entity));
		return mapper.convertToDTO(superHeroRepository.save(entity));
	}

	@Cacheable(value = "superHeroeCache", key = "#id")
	public Optional<SuperHeroDTO> findById(long id) {
		return superHeroRepository.findById(id).map(mapper::convertToDTO);
	}

	@Cacheable(value = "findCache", key = "#fragment")
	public List<SuperHeroDTO> findByNameContaining(String fragment) {
		return (superHeroRepository.findByNameContaining(fragment)).stream().map(mapper::convertToDTO).toList();
	}

	public List<SuperHeroDTO> findAll() {
		return (superHeroRepository.findAll()).stream().map(mapper::convertToDTO).toList();
	}

	public void deleteById(long id) {
		superHeroRepository.deleteById(id);
	}

	public SuperHeroDTO modify(SuperHeroDTO dto) {
		SuperHeroDTO dTO = (findById(dto.getIdSuperhero())
				.orElseThrow(() -> new NotContentW2M(HttpStatus.NO_CONTENT, MESSAGE3)));
		SuperHero entity = mapper.convertToEntity(dTO);

		if (!dto.getCivilIdentity().isBlank()) {
			entity.setCivilIdentity(dto.getCivilIdentity());
		}
		if (!dto.getName().isBlank()) {
			entity.setName(dto.getName());
		}
		if (!dto.getSkillList().get(0).getName().isBlank()) {
			for (int i = 0; i < dto.getSkillList().size(); i++) {
				Skill skill = skillRepository.findById(dto.getSkillList().get(i).getSkillId())
						.orElseThrow(() -> new NotContentW2M(HttpStatus.NO_CONTENT, MESSAGE3));
				skill.setName(dto.getSkillList().get(i).getName());
				entity.getSkillList().set(i, skill);
			}
		}
		return mapper.convertToDTO(superHeroRepository.save(entity));
	}
}
