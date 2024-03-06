package com.w2m.aplicacion.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import com.w2m.aplicacion.dto.SuperHeroDTO;
import com.w2m.aplicacion.dto.SuperHeroDTOEntrance;
import com.w2m.application.adapter.SuperHeroAdapter;
import com.w2m.application.mapper.SuperHeroMapper;
import com.w2m.domain.repository.SuperHeroRepository;
import com.w2m.infrastructure.entity.Skill;
import com.w2m.infrastructure.entity.SuperHero;

@ExtendWith(MockitoExtension.class)
class SuperHeroAdapterTest{

	@Mock
	private SuperHeroRepository superHeroRepository;

	@Spy
	private SuperHeroMapper mapper;

	@InjectMocks
	private SuperHeroAdapter superHeroAdapter;

	@Captor
	private ArgumentCaptor<Long> idArgumentCaptor;

	@Captor
	private ArgumentCaptor<SuperHero> superHeroArgumentCaptor;

	@Test
	void testSave() {
		// Given
		SuperHeroDTOEntrance dto =
				SuperHeroDTOEntrance.builder().build().name("Superman").skillList(new ArrayList<>());

		SuperHero entity = SuperHero.builder().build();
		entity.setName("Superman");
		entity.setIdSuperhero(1L);
		entity.setSkillList(new ArrayList<>());
		when(mapper.convertToEntraceEntity(any())).thenReturn(entity);
		when(superHeroRepository.save(entity)).thenReturn(entity);
		SuperHeroDTO expectedDTO = SuperHeroDTO.builder().name("Superman").build();
		when(mapper.convertToDTO(entity)).thenReturn(expectedDTO);

		// When
		SuperHeroDTO result = superHeroAdapter.save(dto);

		// Then
		assertEquals(expectedDTO, result);
		verify(superHeroRepository, times(1)).save(entity);
	}

	@Test
	void testFindByNameContaining() {
		// Given
		String fragment = "man";
		SuperHero superHero1 = SuperHero.builder().name("Superman")
				.skillList(Arrays.asList(Skill.builder().name("Flying").build())).build();
		SuperHero superHero2 = SuperHero.builder().name("Ironman")
				.skillList(Arrays.asList(Skill.builder().name("Technology").build())).build();
		List<SuperHero> superHeroes = Arrays.asList(superHero1, superHero2);
		when(superHeroRepository.findByNameContaining(fragment)).thenReturn(superHeroes);
		SuperHeroDTO dto1 = SuperHeroDTO.builder().name("Superman").build();
		SuperHeroDTO dto2 = SuperHeroDTO.builder().name("Ironman").build();
		List<SuperHeroDTO> expectedDTOs = Arrays.asList(dto1, dto2);
		when(mapper.convertToDTO(superHero1)).thenReturn(dto1);
		when(mapper.convertToDTO(superHero2)).thenReturn(dto2);

		// When
		List<SuperHeroDTO> result = superHeroAdapter.findByNameContaining(fragment);

		// Then
		assertEquals(expectedDTOs, result);
	}

	@Test
	void testFindById() {
		// Given
		long id = 123;
		SuperHero entity = SuperHero.builder().name("Superman")
				.skillList(Arrays.asList(Skill.builder().name("Flying").build())).build();
		when(superHeroRepository.findById(id)).thenReturn(Optional.of(entity));
		SuperHeroDTO expectedDTO = SuperHeroDTO.builder().name("Superman").build();
		when(mapper.convertToDTO(entity)).thenReturn(expectedDTO);

		// When
		Optional<SuperHeroDTO> result = superHeroAdapter.findById(id);

		// Then
		assertEquals(Optional.of(expectedDTO), result);
	}

	@Test
	void testFindAll() {
		// Given
		List<SuperHero> superHeroes =
				Arrays.asList(SuperHero.builder().idSuperhero(1L).name("Superman").build(),
						SuperHero.builder().idSuperhero(2L).name("Batman").build());
		when(superHeroRepository.findAll()).thenReturn(superHeroes);

		List<SuperHeroDTO> expectedDTOs =
				Arrays.asList(SuperHeroDTO.builder().idSuperhero(1L).name("Superman").build(),
						SuperHeroDTO.builder().idSuperhero(2L).name("Batman").build());
		when(mapper.convertToDTO(any(SuperHero.class))).thenAnswer(invocation -> {
			SuperHero entity = invocation.getArgument(0);
			return SuperHeroDTO.builder().idSuperhero(entity.getIdSuperhero()).name(entity.getName())
					.build();
		});

		// When
		List<SuperHeroDTO> actualDTOs = superHeroAdapter.findAll();

		// Then
		assertEquals(expectedDTOs, actualDTOs);
	}

	@Test
	void testDeleteById() {
		// Given
		long id = 1L;

		// When
		superHeroAdapter.deleteById(id);

		// Then
		verify(superHeroRepository).deleteById(idArgumentCaptor.capture());
		assertEquals(id, idArgumentCaptor.getValue());
	}

}
