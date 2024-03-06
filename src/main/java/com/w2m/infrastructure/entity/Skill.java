package com.w2m.infrastructure.entity;

import java.io.Serializable;
import org.springframework.context.annotation.Description;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Description("This class represents the ability/powers that each superhero has.\n"
		+ " Note that although the name is the same, the ability is not the same,"
		+ " the power of Superman and Wonder Woman to fly is not the same (they do not fly"
		+ " at the same height, nor the same speed...).")
public class Skill implements Serializable{

	private static final long serialVersionUID = 5L;

	@Id
	@Basic(optional = false)
	@Schema(name = "skillId", description = " skill id", example = "2")
	@Column(name = "skill_id")
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long skillId;
	@NotNull
	@Column(name = "skill_name")
	@Schema(name = "name", description = " skill name", example = "super strength")
	private String name;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name = "idSuperhero", referencedColumnName = "id_superhero")
	@ManyToOne(optional = false)
	private SuperHero superHero;
}
