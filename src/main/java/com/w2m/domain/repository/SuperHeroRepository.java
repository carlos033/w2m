package com.w2m.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.w2m.infrastructure.entity.SuperHero;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long>{
	List<SuperHero> findByNameContaining(String name);

}
