package com.w2m.domain.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.w2m.application.adapter.AdministratorAdapter;
import com.w2m.application.service.AdministratorService;
import com.w2m.domain.exception.NotContentW2M;
import com.w2m.infrastructure.entity.Administrator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */

@AllArgsConstructor
@Service
@Slf4j
public class AdministratorImplService implements AdministratorService{
	private static final String NO_CONTENT_MESSAGE = "User not found in DB";
	private final AdministratorAdapter adapter;

	@Override
	public Administrator findById(String usuario) {
		log.info("We call the adapter");
		return adapter.findById(usuario).orElseThrow(() -> {
			log.info(NO_CONTENT_MESSAGE);
			return new NotContentW2M(HttpStatus.NO_CONTENT, NO_CONTENT_MESSAGE);
		});
	}
}
