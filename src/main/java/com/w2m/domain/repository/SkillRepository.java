package com.w2m.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.w2m.infrastructure.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{

}
