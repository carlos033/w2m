package com.w2m.domain.exception;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorResponse{

	private String message;
	private List<String> details;

}
