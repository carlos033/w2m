package com.w2m.infrastructure.entity;

import java.io.Serializable;

import org.springframework.context.annotation.Description;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
@Table(name = "Administrator")
@Description("This class refers to the API manager")
public class Administrator implements Logable, Serializable {

	private static final long serialVersionUID = 4L;
	@Id
	@Basic(optional = false)
	@NotBlank
	@Column(name = "user_name")
	@Schema(name = "username", description = " username", example = "Pedro69")
	private String username;
	@NotBlank
	@Schema(name = "name", description = " name", example = "Pedro")
	private String name;
	@NotBlank
	@Schema(name = "password", description = " user password", example = "1234")
	private String password;

	@Override
	public String getIdentifier() {
		return username;
	}

}
