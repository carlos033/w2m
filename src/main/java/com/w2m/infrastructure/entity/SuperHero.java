package com.w2m.infrastructure.entity;

import java.io.Serializable;
import java.util.List;
import org.springframework.context.annotation.Description;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "super_hero")
@Description("This class represents the super hero with his skill list")
public class SuperHero implements Serializable{
	private static final long serialVersionUID = 6L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_superhero")
	@Schema(name = "idSuperhero", description = "Superhero's id", example = "1")
	private Long idSuperhero;

	@Column(unique = true)
	@Basic(optional = false)
	@NotNull
	@Schema(name = "name", description = "Superhero's name", example = "Batman")
	private String name;

	@Schema(name = "civil_identity", description = "Civil identity when not acting as a superhero",
			example = "Bruce Wayne")
	@Column(name = "civil_identity")
	private String civilIdentity;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "superHero", fetch = FetchType.EAGER)
	@Schema(name = "skillList", description = "List of skills possessed by the superhero")
	private List<Skill> skillList;
}
